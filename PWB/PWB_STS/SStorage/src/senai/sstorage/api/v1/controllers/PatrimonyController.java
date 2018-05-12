package senai.sstorage.api.v1.controllers;

import java.net.URISyntaxException;

import javax.validation.Valid;

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

import com.auth0.jwt.interfaces.DecodedJWT;

import senai.sstorage.api.TemplateController;
import senai.sstorage.authentication.Authority;
import senai.sstorage.authentication.JWTManager;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.UnauthorizedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.Patrimony;
import senai.sstorage.models.User;
import senai.sstorage.service.PatrimonyService;
import senai.sstorage.service.UserService;
import senai.sstorage.utils.WebUtils;

@RestController
@RequestMapping(TemplateController.API_V1 + PatrimonyController.ADDRESS)
public class PatrimonyController extends TemplateController {

	public static final String ADDRESS = "/patrimonies";

	@Autowired
	public PatrimonyService service;

	@Autowired
	public UserService userService;

	@Autowired
	public WebUtils webUtils;

	@PostMapping
	public ResponseEntity<Object> create(@RequestHeader(name = HEADER_TOKEN) String token,
			@RequestBody @Valid Patrimony patrimony, BindingResult br) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			try {
				if (patrimony.getUser() == null) {
					// Setting user from Token
					DecodedJWT decoded = JWTManager.decodeToken(token);
					String userIdString = decoded.getClaim(HEADER_USER_ID).asString();
					User user = userService.read(Long.parseLong(userIdString));
					patrimony.setUser(user);
				}
				service.create(patrimony, br);
				try {
					return ResponseEntity.created(webUtils.getUri(API_V1 + ADDRESS + "/" + patrimony.getId()))
							.body(patrimony);
				} catch (URISyntaxException e) {
					return internalError(e);
				}
			} catch (ValidationException e) {
				return validationError(e, br);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@RequestHeader(name = HEADER_TOKEN) String token,
			@PathVariable(name = "id") Long id) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			// Getting entity from database
			try {
				service.delete(id);
				return ResponseEntity.ok().build();
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping
	public ResponseEntity<Object> get(@RequestHeader(name = HEADER_TOKEN) String token) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			return ResponseEntity.ok(service.read());
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@RequestHeader(name = HEADER_TOKEN) String token,
			@PathVariable(name = "id") Long id) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			try {
				Patrimony obj = service.read(id);
				return ResponseEntity.ok(obj);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestHeader(name = HEADER_TOKEN) String token,
			@PathVariable(name = "id") Long id, @RequestBody @Valid Patrimony patrimony, BindingResult br) {
		try {
			JWTManager.validateToken(token, Authority.ADMINISTRATOR);
			//
			try {
				// Setting user from Token
				DecodedJWT decoded = JWTManager.decodeToken(token);
				String userIdString = decoded.getClaim(HEADER_USER_ID).asString();
				User user = userService.read(Long.parseLong(userIdString));
				if (patrimony.getUser() == null) {
					Patrimony fromDb = service.read(id);
					patrimony.setUser(fromDb.getUser());
				}
				Patrimony updated = service.update(id, patrimony, br);
				//
				return ResponseEntity.ok(updated);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			} catch (ValidationException e) {
				return validationError(e, br);
			}
		} catch (UnauthorizedException e) {
			return unauthorized(e);
		} catch (Exception e) {
			return internalError(e);
		}
	}

}
