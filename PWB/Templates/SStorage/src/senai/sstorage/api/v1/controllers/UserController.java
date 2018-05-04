package senai.sstorage.api.v1.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.sstorage.api.APIAttributes;
import senai.sstorage.authentication.Authority;
import senai.sstorage.authentication.JWTManager;
import senai.sstorage.exceptions.AlreadyLoggedInException;
import senai.sstorage.exceptions.BadRequestException;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.User;
import senai.sstorage.service.UserService;
import senai.sstorage.utils.WebUtils;

@RestController
@RequestMapping(APIAttributes.API_V1 + UserController.ADDRESS)
public class UserController {
	
	public static final String ADDRESS = "/users";
	
	// Logged In Tokens
	private final Map<String, User> loggedTokens = new HashMap<>();

	@Autowired
	private UserService service;
	
	@Autowired
	private WebUtils webUtils;

	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody User user) {
		try {
			// Verifying already logged in User
			Collection<User> loggedUsers = loggedTokens.values();
			for(User _user : loggedUsers) {
				if(_user.getEmail().equalsIgnoreCase(user.getEmail()))
					throw new AlreadyLoggedInException("User is already logged in");
			}
			//
			User fromDb = service.authenticate(user);
			Map<String, String> payloads = new HashMap<String, String>();
			payloads.put(APIAttributes.USER_EMAIL, fromDb.getEmail());
			payloads.put(APIAttributes.USER_AUTH, Authority.parseUserType(fromDb.getType()).toString());
			String token = JWTManager.generateToken(payloads);
			// Registering User Logged In
			loggedTokens.put(token, fromDb);
			//
			return ResponseEntity.ok().header(APIAttributes.API_TOKEN, token).header("Username",  String.format("%s %s", user.getFirstName(), user.getLastName())).build();
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().header(APIAttributes.API_XREASON, "Invalid user supplied").build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().header(APIAttributes.API_XREASON, "Invalid user authentication").build();
		} catch (AlreadyLoggedInException e) {
			return ResponseEntity.badRequest().header(APIAttributes.API_XREASON, "User already logged in").build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(name = APIAttributes.API_TOKEN) String token) {
		try {
			// Removing User from Logged In Tokens
			if(loggedTokens.containsKey(token))
				loggedTokens.remove(token);
			else
				throw new BadRequestException("User not logged in");
			//
			JWTManager.devalidateToken(token);
			return ResponseEntity.ok().build();
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().header(APIAttributes.API_XREASON, e.getMessage()).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(APIAttributes.API_XREASON, e.getMessage()).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestHeader(name = APIAttributes.API_TOKEN) String token, @RequestBody @Valid User user, BindingResult br) {
		try {
			// Authenticate Administrator
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			user.hashPassword();
			User created = service.create(user, br);
			return ResponseEntity.created(webUtils.getUri(APIAttributes.API_V1 + UserController.ADDRESS + "/" + created.getId())).body(created);
		} catch (ValidationException e) {
			return ResponseEntity.unprocessableEntity().header(APIAttributes.API_XREASON, "Validation exception").build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(APIAttributes.API_XREASON, e.getMessage()).build();
		}
	}

}
