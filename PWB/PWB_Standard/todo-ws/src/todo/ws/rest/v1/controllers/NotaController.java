package todo.ws.rest.v1.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo.exceptions.EntidadeNaoEncontradaException;
import todo.exceptions.ValidacaoException;
import todo.models.Nota;
import todo.services.NotaService;
import todo.utils.MapUtils;
import todo.utils.WebUtils;

@RestController
@RequestMapping("/rest/v1/notas")
public class NotaController {

	@Autowired
	private NotaService notaService;

	@Autowired
	private WebUtils webUtils;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable(name = "id") Long id){
		try {
			return ResponseEntity.ok(notaService.buscar(id));
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Reason", "Entidade não encontrada").build();
			
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> buscar(){
		try {
			return ResponseEntity.ok(notaService.buscarTodas());
		
		}catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid Nota nota, BindingResult br) {
		try {
			notaService.cadastrar(nota, br);

			return ResponseEntity.created(webUtils.uri("/rest/v1/notas/" + nota.getId())).body(nota);

		} catch (ValidacaoException e) {

			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).header("X-Reason", "Erro de Validação")
					.body(MapUtils.mapaDe(br));

		} catch (Exception e) {

			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable(name = "id") Long id){
		try {
			notaService.deletar(id);
			
			return ResponseEntity.noContent().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Reason", "Entidade não encontrada").build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> editar(@PathVariable(name = "id") Long id, @RequestBody @Valid Nota nota,
			BindingResult br) {
		try {
			Nota notaAlterada =  notaService.editar(id, nota, br);

			return ResponseEntity.ok(notaAlterada);
			
		} catch (ValidacaoException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).header("X-Reason", "Erro de Validação")
					.body(MapUtils.mapaDe(br));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Reason", "Entidade não encontrada")
					.body(MapUtils.mapaDe(br));
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
