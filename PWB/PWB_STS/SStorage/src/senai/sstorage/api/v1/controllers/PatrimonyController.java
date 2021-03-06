package senai.sstorage.api.v1.controllers;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;

import senai.sstorage.api.TemplateController;
import senai.sstorage.authentication.Authority;
import senai.sstorage.authentication.JwtManager;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.UnauthorizedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.Patrimony;
import senai.sstorage.models.PatrimonyItem;
import senai.sstorage.models.User;
import senai.sstorage.service.PatrimonyItemService;
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
	public PatrimonyItemService patrimonyItemservice;

	@Autowired
	public UserService userService;

	@Autowired
	public WebUtils webUtils;

	@PostMapping
	public ResponseEntity<Object> create(
			@RequestBody @Valid Patrimony patrimony, BindingResult br) {
		try {
			try {
				if (patrimony.getUser() == null) {
					// Setting user from Token
					User user = (User)SecurityContextHolder.getContext().getAuthentication();
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
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
			@PathVariable(name = "id") Long id) {
		try {
			// Getting entity from database
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

	@GetMapping
	public ResponseEntity<Object> get() {
		try {
			return ResponseEntity.ok(service.read());
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> get(
			@PathVariable(name = "id") Long id) {
		try {
			try {
				Patrimony obj = service.read(id);
				return ResponseEntity.ok(obj);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Object> getItems(
			@PathVariable(name = "id") Long id) {
		try {
			List<PatrimonyItem> items = patrimonyItemservice.searchByPatrimony(id);
			return ResponseEntity.ok(items);
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(
			@PathVariable(name = "id") Long id, @RequestBody @Valid Patrimony patrimony, BindingResult br) {
		try {
			try {
				// Setting user from Token
				User user = (User)SecurityContextHolder.getContext().getAuthentication();
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
		} catch (Exception e) {
			return internalError(e);
		}
	}

}