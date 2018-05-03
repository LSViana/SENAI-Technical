package senai.sstorage.api.v1.controllers;

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

	@Autowired
	private UserService service;
	
	@Autowired
	private WebUtils webUtils;

	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody User user) {
		try {
			User fromDb = service.authenticate(user);
			Map<String, String> payloads = new HashMap<String, String>();
			payloads.put(APIAttributes.USER_EMAIL, fromDb.getEmail());
			payloads.put(APIAttributes.USER_AUTH, Authority.parseUserType(fromDb.getType()).toString());
			String token = JWTManager.generateToken(payloads);
			return ResponseEntity.ok().header(APIAttributes.API_TOKEN, token).header("Username",  String.format("%s %s", user.getFirstName(), user.getLastName())).build();
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().header(APIAttributes.API_XREASON, "Invalid user supplied").build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().header(APIAttributes.API_XREASON, "Invalid user authentication").build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(name = APIAttributes.API_TOKEN) String token) {
		try {
			JWTManager.devalidateToken(token);
			return ResponseEntity.ok().build();
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
