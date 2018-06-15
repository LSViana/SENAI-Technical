package br.senai.sp.info.pweb.jucacontrol.ws.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public abstract class TemplateController {
	
	public ResponseEntity<Object> validationException(Exception e, BindingResult br) {
		BodyBuilder bb = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);
		//
		for(FieldError fe : br.getFieldErrors()) {
			bb.header("X-" + fe.getObjectName(), fe.getDefaultMessage());
		}
		//
		return bb.build();
	}
	
	public ResponseEntity<Object> notFound(Exception e) {
		return ResponseEntity.notFound().build();
	}

}
