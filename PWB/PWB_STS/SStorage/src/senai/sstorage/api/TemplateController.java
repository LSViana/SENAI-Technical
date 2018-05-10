package senai.sstorage.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import senai.sstorage.utils.WebUtils;

@Component
public abstract class TemplateController {
	
	public static final String VALIDATION_EXCEPTION = "Validation exception";
	public static final String INVALID_ENTITY_AUTHENTICATION = "Invalid entity authentication";
	public static final String INVALID_ENTITY_SUPPLIED = "Invalid entity supplied";
	public static final String NOT_ENOUGH_AUTHORITY = "Not enough authority";
	public static final String NOT_ALLOWED = "Not allowed";
	public static final String API_V1 = "/api/v1";
	// Headers
	public static final String HEADER_XREASON = "X-Reason";
	public static final String HEADER_XEXCEPTIONMESSAGE = "X-Exception-Message";
	public static final String HEADER_TOKEN = "X-Auth-Token";
	public static final String HEADER_USER_ID = "X-Id";
	public static final String HEADER_USER_EMAIL = "X-Email";
	public static final String HEADER_USER_AUTH = "X-User-Auth";
	public static final String HEADER_USERNAME = "X-Username";
	public static final String HEADER_ID = "X-Id";
	public static final String HEADER_AUTH_NUMBER = "X-Auth-Level";
	
	public ResponseEntity<Object> entityNotFound(Exception e) {
		return ResponseEntity.notFound().header(HEADER_XREASON, INVALID_ENTITY_AUTHENTICATION).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}

	public ResponseEntity<Object> validationError(Exception e) {
		return ResponseEntity.unprocessableEntity().header(HEADER_XREASON, VALIDATION_EXCEPTION).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}
	
	public ResponseEntity<Object> validationError(Exception e, BindingResult br) {
		BodyBuilder bb = ResponseEntity.unprocessableEntity().header(HEADER_XREASON, VALIDATION_EXCEPTION).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage());
		for(FieldError fe : br.getFieldErrors()) {
			bb.header("X-" + fe.getField(), fe.getDefaultMessage());
		}
		return bb.build();
	}

	public ResponseEntity<Object> unauthorized(Exception e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header(HEADER_XREASON, NOT_ENOUGH_AUTHORITY).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}

	public ResponseEntity<Object> internalError(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}

	public ResponseEntity<Object> invalidEntitySupplied(Exception e) {
		return ResponseEntity.badRequest().header(HEADER_XREASON, INVALID_ENTITY_SUPPLIED).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}
	
	public ResponseEntity<Object> notAllowed(Exception e) {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HEADER_XREASON, INVALID_ENTITY_SUPPLIED).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}

}
