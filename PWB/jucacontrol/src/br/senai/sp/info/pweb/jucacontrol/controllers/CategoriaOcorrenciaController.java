package br.senai.sp.info.pweb.jucacontrol.controllers;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.dao.jpa.CategoriaOcorrenciaJPA;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Controller
@RequestMapping("/app/adm")
public class CategoriaOcorrenciaController {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaOcorrenciaDAO;
	
	@GetMapping("/categoria")
	public String abrirMenuCategorias(@RequestParam(name = "id", required = false) Long id,
										Model model) {
		
		if(id != null) {
			model.addAttribute("categoriaOcorrencia", categoriaOcorrenciaDAO.buscar(id));
		}else {
			model.addAttribute("categoriaOcorrencia", new CategoriaOcorrencia());
		}
		
		
		model.addAttribute("categorias", categoriaOcorrenciaDAO.buscarTodos());
		return "categoria/menu";
	}

	@GetMapping("/categoria/deletar")
	public String deletar(@RequestParam(name = "id", required = true) Long id) {
		
		categoriaOcorrenciaDAO.deletar(categoriaOcorrenciaDAO.buscar(id));
		
		return "redirect:/app/adm/categoria";
	}
	
	
	@PostMapping("/categoria/salvar")
	public String salvar(@Valid CategoriaOcorrencia categoriaOcorrencia, BindingResult brCategoria) {
		
		if(categoriaOcorrenciaDAO.buscarPorNome(categoriaOcorrencia.getNome()) != null) {
			brCategoria.addError(new FieldError("categoriaOcorrencia", "nome", 
						"O nome '" + categoriaOcorrencia.getNome() + "' já esta em uso. Escolha outro"));
		}
		
		//Verificar se houve erros
		if(brCategoria.hasErrors()) {
			return "categoria/menu";
		}
		

		if(categoriaOcorrencia.getId() == null) {
			categoriaOcorrenciaDAO.persistir(categoriaOcorrencia);
		}else {
			categoriaOcorrenciaDAO.alterar(categoriaOcorrencia);
		}
		
		
		return "redirect:/app/adm/categoria";
	}

}
