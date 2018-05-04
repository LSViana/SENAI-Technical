package senai.sstorage.api.v1.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.sstorage.api.TemplateController;
import senai.sstorage.authentication.Authority;
import senai.sstorage.authentication.JWTManager;
import senai.sstorage.exceptions.BadRequestException;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.UnauthorizedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.User;
import senai.sstorage.service.UserService;
import senai.sstorage.utils.WebUtils;

@RestController
@RequestMapping(TemplateController.API_V1 + UserController.ADDRESS)
public class UserController extends TemplateController {

	public static final String ADDRESS = "/users";

	@Autowired
	private UserService service;
	
	@Autowired
	private WebUtils webUtils;
	
	private Map<String, User> activeTokens = new HashMap<>();

	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody User user) {
		try {
			User fromDb = service.authenticate(user);
			Map<String, String> payloads = new HashMap<String, String>();
			payloads.put(HEADER_USER_ID, fromDb.getId().toString());
			payloads.put(HEADER_USER_EMAIL, fromDb.getEmail());
			payloads.put(HEADER_USER_AUTH, Authority.parseUserType(fromDb.getType()).toString());
			String token = JWTManager.generateToken(payloads);
			activeTokens.put(token, fromDb);
			return ResponseEntity.ok().header(HEADER_TOKEN, token).header("Username",  String.format("%s %s", fromDb.getFirstName(), fromDb.getLastName())).build();
		} catch (BadRequestException e) {
			return invalidEntitySupplied(e);
		} catch (EntityNotFoundException e) {
			return entityNotFound(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(name = HEADER_TOKEN) String token) {
		try {
			if(activeTokens.containsKey(token)) {
				JWTManager.devalidateToken(token);
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
	public ResponseEntity<Object> create(@RequestHeader(name = HEADER_TOKEN) String token, @RequestBody @Valid User user, BindingResult br) {
		try {
			// Authenticate Administrator
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			user.hashPassword();
			User created = service.create(user, br);
			return ResponseEntity.created(webUtils.getUri(API_V1 + ADDRESS + "/" + created.getId())).body(created);
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (ValidationException e) {
			return validationError(e);
		} catch(Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> get(@RequestHeader(name = HEADER_TOKEN) String token) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			// Returning User List
			Collection<User> users = service.read();
			return ResponseEntity.ok(users);
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@RequestHeader(name = HEADER_TOKEN) String token, @PathVariable(name = "id") Long id) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			try {
				User obj = service.read(id);
				return ResponseEntity.ok(obj);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestHeader(name = HEADER_TOKEN) String token, @PathVariable(name = "id") Long id, @RequestBody @Valid User user, BindingResult br) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			try {
				User updated = service.update(id, user, br);
				return ResponseEntity.ok(updated);
			} catch (ValidationException e) {
				return validationError(e);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@RequestHeader(name = HEADER_TOKEN) String token, @PathVariable(name = "id") Long id) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			try {
				service.delete(id);
				return ResponseEntity.ok().build();
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
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
