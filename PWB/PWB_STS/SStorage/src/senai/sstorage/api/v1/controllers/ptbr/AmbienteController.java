package senai.sstorage.api.v1.controllers.ptbr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.sstorage.api.v1.controllers.EnvironmentController;

@RestController
@RequestMapping("/rest/ambientes")
public class AmbienteController {

	@Autowired
	private EnvironmentController envController;
	
	@GetMapping
	public ResponseEntity<Object> listar() {
		return envController.get();
	}
	
}
