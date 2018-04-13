package br.senai.sp.info.pweb.jucacontrol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

@Controller
@RequestMapping("/app")
public class OcorrenciaController {
		
	@GetMapping({"", "/"})
	public String abrirListaOcorrencia(Model model) {
		
		return "ocorrencia/lista";
	}
	
	@GetMapping({"/ocorrencia/nova"})
	public String abriFormOcorrencia(Model model) {
		
		return "ocorrencia/form";
	}
	
	@GetMapping("/ocorrencia/editar")
	public String abrirEditarOcorrencia(@RequestParam(required = true) Long id, Model model) {
		
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
	public String salvar(Ocorrencia ocorrencia) {		
		
		return "redirect:/app";
	}
	

}
