package senai.sstorage.api.v1.controllers;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import senai.sstorage.models.Environment;
import senai.sstorage.models.ItemState;
import senai.sstorage.models.Movement;
import senai.sstorage.models.PatrimonyItem;
import senai.sstorage.models.User;
import senai.sstorage.service.EnvironmentService;
import senai.sstorage.service.MovementService;
import senai.sstorage.service.PatrimonyItemService;
import senai.sstorage.service.UserService;
import senai.sstorage.utils.WebUtils;

@RestController
@RequestMapping(TemplateController.API_V1 + MovementController.ADDRESS)
public class MovementController extends TemplateController {
	
	public static final String ADDRESS = "/movements";
	
	@Autowired
	private MovementService service;
	
	@Autowired
	private PatrimonyItemService piService;
	
	@Autowired
	private EnvironmentService envService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WebUtils webUtils;
	
	@GetMapping("/move/{idPatrimonyItem}/{idEnvDestiny}")
	public ResponseEntity<Object> movement(@PathVariable(name = "idPatrimonyItem") Long idPatrimonyItem, @PathVariable(name = "idEnvDestiny") Long idEnvDestiny) {
		try {
			Long userId = ((User)SecurityContextHolder.getContext().getAuthentication()).getId();
			// Getting Relationed Entities
			User loggedUser = userService.read(userId);
			PatrimonyItem pi = piService.read(idPatrimonyItem);
			if(pi.getState() == ItemState.REMOVED) {
				throw new UnauthorizedException("You can't move a removed item");
			}
			Environment destiny = envService.read(idEnvDestiny);
			if(destiny == null) {
				return entityNotFound(new Exception("Destiny environment not found"));
			}
			//
			if(loggedUser == null) {
				throw new BadRequestException("Invalid Token Supplied");
			}
			Movement movement = service.movement(pi, destiny, loggedUser);
			return ResponseEntity.created(webUtils.getUri(API_V1 + ADDRESS + "/" + movement.getId())).body(movement);
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
		} catch (ValidationException e) {
			return ResponseEntity.unprocessableEntity().header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
		} catch (Exception e) {
			return internalError(e);
		}
	}

	@GetMapping("/bypatrimony/{id}")
	public ResponseEntity<Object> searchByPatrimonyItem(@PathVariable(name = "id") Long id) {
		try {
			PatrimonyItem patrimonyItem;
			try {
				patrimonyItem = piService.read(id);
			} catch (EntityNotFoundException e) {
				return entityNotFound(e);
			}
			return ResponseEntity.ok(service.searchByPatrimonyItem(patrimonyItem));
		} catch (Exception e) {
			return internalError(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.ok(service.read(id));
		} catch (Exception e) {
			return internalError(e);
		}
	}

}
