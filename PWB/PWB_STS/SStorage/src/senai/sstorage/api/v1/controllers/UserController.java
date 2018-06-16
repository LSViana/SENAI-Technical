package senai.sstorage.api.v1.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;

import senai.sstorage.api.TemplateController;
import senai.sstorage.authentication.Authority;
import senai.sstorage.authentication.JwtManager;
import senai.sstorage.exceptions.BadRequestException;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.UnauthorizedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.User;
import senai.sstorage.service.UserService;
import senai.sstorage.service.models.ChangeNames;
import senai.sstorage.service.models.ChangePassword;
import senai.sstorage.utils.EmailUtils;
import senai.sstorage.utils.WebUtils;

@RestController
@RequestMapping(TemplateController.API_V1 + UserController.ADDRESS)
public class UserController extends TemplateController {

	public static final String ADDRESS = "/users";

	@Autowired
	private UserService service;
	
	@Autowired
	private WebUtils webUtils;
	
	@Autowired
	private Validator validator;
	
	private Map<String, User> activeTokens = new HashMap<>();
	
	@PostMapping("/map")
	public ResponseEntity<Object> testMap(@RequestBody Map<String, String> maps) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody(required = true) User user) {
		try {
			// Verifying if the user is already logged in
//			Collection<User> usersLoggedIn = activeTokens.values();
//			for(User loggedIn : usersLoggedIn) {
//				if(loggedIn.getEmail().equalsIgnoreCase(user.getEmail()))
//					return ResponseEntity.status(HttpStatus.CONFLICT).header(HEADER_XREASON, "This user is already authenticated.").build();
//			}
			//
			User fromDb = service.authenticate(user);
			Authority authority = Authority.parseUserType(fromDb.getType());
			Map<String, String> payloads = new HashMap<String, String>();
			payloads.put(HEADER_USER_ID, fromDb.getId().toString());
			payloads.put(HEADER_USER_EMAIL, fromDb.getEmail());
			payloads.put(HEADER_USER_AUTH, authority.toString());
			String token = JwtManager.generateToken(payloads);
			activeTokens.put(token, fromDb);
			return ResponseEntity.ok().header(HEADER_TOKEN, token).header(HEADER_USERNAME,  String.format("%s %s", fromDb.getFirstName(), fromDb.getLastName())).header(HEADER_ID, fromDb.getId().toString()).header(HEADER_AUTH_NUMBER, authority.getLevel().toString()).build();
		} catch (BadRequestException e) {
			return invalidEntitySupplied(e);
		} catch (EntityNotFoundException e) {
			return entityNotFound(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updatePartial(@PathVariable(name = "id") Long id, @RequestBody @Valid User user, BindingResult br) {
		try {
			User fromDb = service.read(id);
			//
			BindingResult freshBr = new DataBinder(user).getBindingResult();
			if(fromDb != null) {
				if(user.getPassword() == null || user.getPassword().length() == 0) {
					user.setPassword(fromDb.getPassword());
					validator.validate(user, freshBr);
				}
				else {
					validator.validate(user, freshBr);
					user.hashPassword();
				}
				br = freshBr;
			} else
				throw new EntityNotFoundException();
			//
			BeanUtils.copyProperties(user, fromDb);
			// Performing the common changes
			return update(id, fromDb, br);
		} catch (EntityNotFoundException e) {
			return entityNotFound(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@PatchMapping("/changenames")
	public ResponseEntity<Object> logout(
			@RequestBody(required = true) ChangeNames changeNames) {
		BindingResult br;
		try {
			br = new DataBinder(changeNames).getBindingResult();
			//
			User currentUser = (User)SecurityContextHolder.getContext().getAuthentication();
			try {
				service.changeNames(currentUser, changeNames, br);
				return ResponseEntity.ok(currentUser);
			} catch (ValidationException e) {
				return validationError(e, br);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@PatchMapping("/changepassword")
	public ResponseEntity<Object> logout(@RequestBody(required = true) ChangePassword changePassword) {
		BindingResult br;
		try {
			br = new DataBinder(changePassword).getBindingResult();
			//
			User currentUser = (User)SecurityContextHolder.getContext().getAuthentication();
			try {
				service.changePassword(currentUser, changePassword, br);
				return ResponseEntity.ok(currentUser);
			} catch (ValidationException e) {
				return validationError(e, br);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(name="Authorization") String auth) {
		try {
			String token = auth.split(" ")[1];
			if(activeTokens.containsKey(token)) {
				JwtManager.devalidateToken(token);
				activeTokens.remove(token);
				return ResponseEntity.ok().build();	
			}
			throw new BadRequestException("Token already logged out");
		} catch (BadRequestException e) { 
			return ResponseEntity.badRequest().header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody(required = true) @Valid User user, BindingResult br) {
		try {
			User created = service.create(user, br);
			// Sending email to the newly registered user
			EmailUtils.sendEmail("Welcome to SStorage! The SENAI Software to manage patrimonies", "You've been successfully registered, you can go to our login page and start your experience.", user.getEmail());
			//
			return ResponseEntity.created(webUtils.getUri(API_V1 + ADDRESS + "/" + created.getId())).body(created);
		} catch (ValidationException e) {
			return validationError(e, br);
		} catch(Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> get() {
		try {
			// Returning User List
			Collection<User> users = service.read();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable(name = "id") Long id) {
		try {
			try {
				User obj = service.read(id);
				return ResponseEntity.ok(obj);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody(required = true) @Valid User user, BindingResult br) {
		try {
			try {
				User updated = service.update(id, user, br);
				return ResponseEntity.ok(updated);
			} catch (ValidationException e) {
				return validationError(e, br);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		try {
			try {
				service.delete(id);
				return ResponseEntity.ok().build();
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	// Getters and Setters to Interaction
	public Map<String, User> getActiveTokens() {
		return activeTokens;
	}

	public void setActiveTokens(Map<String, User> activeTokens) {
		this.activeTokens = activeTokens;
	}

}
