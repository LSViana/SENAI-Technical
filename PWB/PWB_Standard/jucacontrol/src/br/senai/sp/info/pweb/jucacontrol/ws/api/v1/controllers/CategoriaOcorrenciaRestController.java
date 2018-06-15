package br.senai.sp.info.pweb.jucacontrol.ws.api.v1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.jucacontrol.exceptions.ValidationException;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.services.CategoriaOcorrenciaService;
import br.senai.sp.info.pweb.jucacontrol.ws.api.TemplateController;

@RestController
@RequestMapping("/rest/categorias")
public class CategoriaOcorrenciaRestController extends TemplateController {
	
	@Autowired
	private CategoriaOcorrenciaService service;
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid CategoriaOcorrencia valor, BindingResult br) {
		try {
			service.cadastrar(valor, br);
			//
			return ResponseEntity.ok(valor);
		} catch (ValidationException e) {
			return validationException(e, br);
		}
	}

}
