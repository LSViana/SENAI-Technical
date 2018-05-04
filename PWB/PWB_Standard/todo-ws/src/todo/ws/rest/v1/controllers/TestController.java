package todo.ws.rest.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/tests")
public class TestController {
	
	//Response Entity - Objeto =que encapsula o retorno de um web service
	
	@GetMapping
	public ResponseEntity<Void> metodoGet() {
		return ResponseEntity.ok().build();
	}

}
