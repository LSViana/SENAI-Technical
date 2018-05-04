package senai.sstorage.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	public static final String HEADER_TOKEN = "Token";
	public static final String HEADER_USER_ID = "Id";
	public static final String HEADER_USER_EMAIL = "Email";
	public static final String HEADER_USER_AUTH = "User-Auth";

	public ResponseEntity<Object> entityNotFound(Exception e) {
		return ResponseEntity.notFound().header(HEADER_XREASON, INVALID_ENTITY_AUTHENTICATION).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
	}

	public ResponseEntity<Object> validationError(Exception e) {
		return ResponseEntity.unprocessableEntity().header(HEADER_XREASON, VALIDATION_EXCEPTION).header(HEADER_XEXCEPTIONMESSAGE, e.getMessage()).build();
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
