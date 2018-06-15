package senai.sstorage.api.v1.controllers.ptbr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.sstorage.api.v1.controllers.UserController;
import senai.sstorage.models.User;

@RestController
@RequestMapping("/rest/auth")
public class AutenticacaoController {
	
	@Autowired
	private UserController userController;
	
	@PostMapping("/jwt")
	public ResponseEntity<Object> autenticar(@RequestBody User user) {
		return userController.authenticate(user);
	}

}
