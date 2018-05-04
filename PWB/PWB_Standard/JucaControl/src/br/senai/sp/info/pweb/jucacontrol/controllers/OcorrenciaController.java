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
import br.senai.sp.info.pweb.jucacontrol.models.BuscarPorSituacaoOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Controller
@RequestMapping("/app")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaDAO ocorrenciaDAO;

	@Autowired
	private CategoriaOcorrenciaDAO catOcorrenciaDAO;

	@GetMapping({ "", "/" })
	public String abrirListaOcorrencia(@RequestParam(name="pesquisa", required=false) String pesquisa, Model model) {
		model.addAttribute("tiposBusca", BuscarPorSituacaoOcorrencia.values());	
		try {			
			BuscarPorSituacaoOcorrencia bpso = BuscarPorSituacaoOcorrencia.valueOf(pesquisa);
			model.addAttribute("ocorrencias", ocorrenciaDAO.buscarPorSituacao(bpso));
		} catch(Exception e) {
			model.addAttribute("ocorrencias", ocorrenciaDAO.buscarTodos());
		}
		//
		return "ocorrencia/lista";
	}

	@GetMapping({ "/ocorrencia/nova" })
	public String abriFormOcorrencia(Model model) {
		model.addAttribute("ocorrencia", new Ocorrencia());
		model.addAttribute("categorias", catOcorrenciaDAO.buscarTodos());
		return "ocorrencia/form";
	}

	@GetMapping("/ocorrencia/editar")
	public String abrirEditarOcorrencia(@RequestParam(required = true) Long id, Model model) {
		Ocorrencia ocorrencia = ocorrenciaDAO.buscar(id);
		if (ocorrencia == null)
			ocorrencia = new Ocorrencia();
		model.addAttribute("ocorrencia", ocorrencia);
		model.addAttribute("categorias", catOcorrenciaDAO.buscarTodos());
		return "ocorrencia/form";
	}

	@GetMapping("/ocorrencia/assumir")
	public String assumirOcorrencia(@RequestParam(required = true) Long id, HttpSession sessao) {
		// Obtendo a sessão pelo ID
		Ocorrencia ocorrencia = ocorrenciaDAO.buscar(id);
		// Apenas realiza operação se a ocorrência ainda não tiver um técnico
		if (ocorrencia.getTecnico() == null) {
			// Pega o usuário logado
			Usuario atual = (Usuario) sessao.getAttribute(UsuarioController.USUARIO_KEY);
			// Seta o usuário logado como quem atendeu a ocorrência
			ocorrencia.setTecnico(atual);
			ocorrencia.setDataModificacao(new Date());
			// Salvando a ocorrência no banco
			ocorrenciaDAO.alterar(ocorrencia);
		}
		return "redirect:/app";
	}

	@GetMapping("/ocorrencia/encerrar")
	public String concluirOcorrencia(@RequestParam(required = true) Long id, HttpSession sessao) {
		Ocorrencia ocorrencia = ocorrenciaDAO.buscar(id);
		/*
			1º - Deve ter sido atendido;
			2º - A ocorrência não deve ter sido concluída;
			3º - O emissor da ocorrência deve ser o usuário logado
		*/
		if (ocorrencia.getTecnico() != null && ocorrencia.getDataConclusao() == null) {
			Usuario atual = (Usuario) sessao.getAttribute(UsuarioController.USUARIO_KEY);
			if(ocorrencia.getTecnico().getId() == atual.getId() || ocorrencia.getEmissor().getId() == atual.getId()) {
				ocorrencia.setDataModificacao(new Date());
				ocorrencia.setDataConclusao(new Date());
				ocorrenciaDAO.alterar(ocorrencia);
			}
		}
		return "redirect:/app";
	}

	@PostMapping("/ocorrencia/salvar")
	public String salvar(@Valid Ocorrencia ocorrencia, BindingResult br, HttpSession sessao, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("categorias", catOcorrenciaDAO.buscarTodos());
			return "ocorrencia/form";
		}
		ocorrencia.setDataModificacao(new Date());
		Usuario u = (Usuario) sessao.getAttribute(UsuarioController.USUARIO_KEY);
		ocorrencia.setEmissor(u);
		if (ocorrencia.getId() == null) {
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