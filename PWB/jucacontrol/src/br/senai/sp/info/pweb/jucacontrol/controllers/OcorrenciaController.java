package br.senai.sp.info.pweb.jucacontrol.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.dao.OcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Controller
@RequestMapping("/app")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaDAO ocorrenciaDAO;
	
	@Autowired
	private CategoriaOcorrenciaDAO catOcorrenciaDAO;

	@GetMapping({"", "/"})
	public String abrirListaOcorrencia(Model model) {
		model.addAttribute("ocorrencias", ocorrenciaDAO.buscarTodos());
		return "ocorrencia/lista";
	}

	@GetMapping({"/ocorrencia/nova"})
	public String abriFormOcorrencia(Model model) {
		model.addAttribute("ocorrencia", new Ocorrencia());
		model.addAttribute("categorias", catOcorrenciaDAO.buscarTodos());
		return "ocorrencia/form";
	}

	@GetMapping("/ocorrencia/editar")
	public String abrirEditarOcorrencia(@RequestParam(required = true) Long id, Model model) {
		Ocorrencia ocorrencia = ocorrenciaDAO.buscar(id);
		if(ocorrencia == null)
			ocorrencia = new Ocorrencia();
		model.addAttribute("ocorrencia", ocorrencia);
		model.addAttribute("categorias", catOcorrenciaDAO.buscarTodos());
		return "ocorrencia/form";
	}

	@GetMapping("/ocorrencia/assumir")
	public String assumirOcorrencia(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {

		return "redirect:/app";
	}

	@GetMapping("/ocorrencia/encerrar")
	public String concluirOcorrencia(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {
		return "redirect:/app";
	}

	@PostMapping("/ocorrencia/salvar")
	public String salvar(@Valid Ocorrencia ocorrencia, @RequestParam(required = true) Long categoriaId, BindingResult br, HttpSession sessao) {
		if(br.hasErrors()) {
			return "ocorrencia/form";
		}
		ocorrencia.setDataModificacao(new Date());
		ocorrencia.setCategoria(catOcorrenciaDAO.buscar(categoriaId));
		Usuario u = (Usuario)sessao.getAttribute(UsuarioController.USUARIO_KEY);
		ocorrencia.setEmissor(u);
		if(ocorrencia.getId() == null) {
			ocorrencia.setDataCadastro(new Date());
			ocorrenciaDAO.persistir(ocorrencia);
		} else {
			Ocorrencia _ocorrencia = ocorrenciaDAO.buscar(ocorrencia.getId());
			ocorrencia.setDataCadastro(_ocorrencia.getDataCadastro());
			ocorrenciaDAO.alterar(ocorrencia);
		}
		return "redirect:/app";
	}


}
