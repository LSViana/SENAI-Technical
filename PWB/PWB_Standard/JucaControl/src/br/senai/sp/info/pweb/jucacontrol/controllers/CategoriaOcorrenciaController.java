package br.senai.sp.info.pweb.jucacontrol.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

//@Controller
//@RequestMapping("/app/adm")
public class CategoriaOcorrenciaController {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaOcorrenciaDAO;
	
	@GetMapping("/categoria")
	public String abrirMenuCategorias(@RequestParam(name = "id", required = false) Long id,
										Model model) {
		
		if(id == null) {
			//Passa o objeto novo de categoria
			model.addAttribute("categoriaOcorrencia", new CategoriaOcorrencia());
		}else {
			//Passa a categoria buscando pelo id
			model.addAttribute("categoriaOcorrencia", categoriaOcorrenciaDAO.buscar(id));
		}
		
		//Buscar todas as categorias
		model.addAttribute("categorias", categoriaOcorrenciaDAO.buscarTodos());
		
		
		return "categoria/menu";
	}

	@GetMapping("/categoria/deletar")
	public String deletar(@RequestParam(name = "id", required = true) Long id) {
		
		categoriaOcorrenciaDAO.deletar(categoriaOcorrenciaDAO.buscar(id));
		return "redirect:/app/adm/categoria";
	}
	
	
	@PostMapping("/categoria/salvar")
	public String salvar(@Valid CategoriaOcorrencia categoriaOcorrencia, BindingResult brCategoriaOcorrencia,
						Model model) {		
		
		//Verificando se o nome já existe
		if(categoriaOcorrenciaDAO.buscarPorNome(categoriaOcorrencia.getNome()) != null) {
			brCategoriaOcorrencia.addError(new FieldError("categoriaOcorrencia", "nome", "O nome já existe"));
		}
		
		//Reaizando validações dos campos
		if(brCategoriaOcorrencia.hasErrors()) {
			//Passamos o objeto pelo Model pois o nome do modelAttribute no  menu.jsp é "categoria"
			model.addAttribute("categoriaOcorrencia", categoriaOcorrencia);
			model.addAttribute("categorias", categoriaOcorrenciaDAO.buscarTodos());
			return "categoria/menu";
		}
		
		///Persistindo no banco de dados
		if(categoriaOcorrencia.getId() == null) {
			categoriaOcorrenciaDAO.persistir(categoriaOcorrencia);
		}else {
			CategoriaOcorrencia categoriaBanco = categoriaOcorrenciaDAO.buscar(categoriaOcorrencia.getId());
			BeanUtils.copyProperties(categoriaOcorrencia, categoriaBanco, "id");
			categoriaOcorrenciaDAO.alterar(categoriaBanco);
		}
		
		
		//Redireciona para pagina de categorias
		return "redirect:/app/adm/categoria";
	}

}
