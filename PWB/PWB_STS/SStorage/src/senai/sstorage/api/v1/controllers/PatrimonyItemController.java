package senai.sstorage.api.v1.controllers;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;

import senai.sstorage.api.TemplateController;
import senai.sstorage.authentication.Authority;
import senai.sstorage.authentication.JwtManager;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.UnauthorizedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.PatrimonyItem;
import senai.sstorage.models.User;
import senai.sstorage.service.EnvironmentService;
import senai.sstorage.service.PatrimonyItemService;
import senai.sstorage.service.PatrimonyService;
import senai.sstorage.service.UserService;
import senai.sstorage.utils.WebUtils;

@RestController
@RequestMapping(TemplateController.API_V1 + PatrimonyItemController.ADDRESS)
public class PatrimonyItemController extends TemplateController {

	public static final String ADDRESS = "/patrimonyitems";

	@Autowired
	private PatrimonyItemService service;

	@Autowired
	private WebUtils webUtils;

	@PostMapping
	public ResponseEntity<Object> create(
			@RequestBody @Valid PatrimonyItem pi, BindingResult br) {
		try {
			try {
				if (pi.getUser() == null) {
					// Setting user from Token
					User user = (User) SecurityContextHolder.getContext().getAuthentication();
					pi.setUser(user);
				}
				PatrimonyItem created = service.create(pi, br);
				try {
					return ResponseEntity.created(webUtils.getUri(API_V1 + ADDRESS + "/" + created.getId()))
							.body(created);
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

	@GetMapping("/requestremoval/{id}")
	public ResponseEntity<Object> performRequestRemoval(
			@PathVariable(name = "id") Long id) {
		try {
			try {
				return ResponseEntity.ok(service.requestRemoval(id));
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/activate/{id}")
	public ResponseEntity<Object> performActivation(
			@PathVariable(name = "id") Long id) {
		try {
			try {
				return ResponseEntity.ok(service.activate(id));
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping("/remove/{id}")
	public ResponseEntity<Object> performRemoval(
			@PathVariable(name = "id") Long id) {
		try {
			try {
				return ResponseEntity.ok(service.remove(id));
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

	@GetMapping("/removerequested")
	public ResponseEntity<Object> getRemoveRequested() {
		try {
			return ResponseEntity.ok(service.readRemoveRequested());
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping("/removed")
	public ResponseEntity<Object> getRemoved() {
		try {
			return ResponseEntity.ok(service.readRemoved());
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> get(
			@PathVariable(name = "id") Long id) {
		try {
			try {
				PatrimonyItem obj = service.read(id);
				return ResponseEntity.ok(obj);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
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

}
