package senai.sstorage.api.v1.controllers.ptbr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.sstorage.api.v1.controllers.MovementController;
import senai.sstorage.api.v1.controllers.PatrimonyItemController;
import senai.sstorage.models.Environment;

@RestController
@RequestMapping("/rest/itens")
public class ItemDoPatrimonio {
	
	@Autowired
	private PatrimonyItemController patItemController;
	
	@Autowired
	private MovementController movController;
	
	@GetMapping
	public ResponseEntity<Object> listar() {
		return patItemController.get();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> buscar(@PathVariable(name="id") Long id) {
		return patItemController.get(id);
	}
	
	@PostMapping("{id}/movimentacoes")
	public ResponseEntity<Object> movimentar(@PathVariable(name="id") Long idPatrimonyItem, @RequestBody Environment env) {
		if(env.getId() == null)
			return ResponseEntity.badRequest().build();
		return movController.movement(idPatrimonyItem, env.getId());
	}
	
	@GetMapping("{id}/movimentacoes")
	public ResponseEntity<Object> listarMovimentacoes(@PathVariable(name="id") Long id) {
		return movController.searchByPatrimonyItem(id);
	}

}
