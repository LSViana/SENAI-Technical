package br.senai.sp.info.pweb.jucacontrol.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.dao.OcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.BuscaPorSituacaoDeOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

//@Controller
//@RequestMapping("/app")
public class OcorrenciaController {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaDAO;
	
	@Autowired
	private OcorrenciaDAO ocorrenciaDAO;
		
	/**
	 * 
	 * @param model
	 * @param situacao - Parâmetro GET (pesquisa) que define o filtro de busca de ocorrências
	 * @return
	 */
	@GetMapping({"", "/"})
	public String abrirListaOcorrencia(	Model model, 
					@RequestParam(name = "pesquisa", required = false) BuscaPorSituacaoDeOcorrencia situacao) {
		
		//Caso a situação não seja passada consideraremos como filtro: TODOS
		if(situacao == null) {
			situacao = BuscaPorSituacaoDeOcorrencia.TODOS;
		}
		
		model.addAttribute("ocorrencias", ocorrenciaDAO.buscarPorSituacao(situacao));
		
		//Manda as enumerações de busca para a tela
		model.addAttribute("pesquisas", BuscaPorSituacaoDeOcorrencia.values());
		
		return "ocorrencia/lista";
	}
	
	@GetMapping({"/ocorrencia/nova"})
	public String abriFormOcorrencia(Model model) {
		
		//Enviando as categorias para a página
		model.addAttribute("categorias", categoriaDAO.buscarTodos());
		
		//Enviando o modelo pro modelAttribute
		model.addAttribute(new Ocorrencia());
		
		return "ocorrencia/form";
	}
	
	@GetMapping("/ocorrencia/editar")
	public String abrirEditarOcorrencia(@RequestParam(required = true) Long id, Model model) {
		
		//Passando a ocorrencia para a pagina
		model.addAttribute("ocorrencia", ocorrenciaDAO.buscar(id));
		model.addAttribute("categorias", categoriaDAO.buscarTodos());
		
		return "ocorrencia/form";
	}
	
	@GetMapping("/ocorrencia/assumir")
	public String assumirOcorrencia(@RequestParam(required = true) Long id, HttpSession session) {
		//Pegando a ocorrência que será assumida através do ID
		Ocorrencia o = ocorrenciaDAO.buscar(id);
		
		//Pegando o usuário logado para determinar que ele assumirá a ocorrência
		Usuario logado = (Usuario) session.getAttribute("usuarioAutenticado");
		
		//Modificando a ocorrência...
		o.setDataModificacao(new Date());
		o.setTecnico(logado);
		
		ocorrenciaDAO.alterar(o);
		
		return "redirect:/app";
	}
	
	@GetMapping("/ocorrencia/encerrar/tecnico")
	public String concluirOcorrenciaTecnico(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {
		
		//Pega a ocorrência e define a data de conclusão do técnico
		Ocorrencia o = ocorrenciaDAO.buscar(id);
		o.setDataConclusaoTecnico(new Date());
		ocorrenciaDAO.alterar(o);
		
		
		return "redirect:/app";
	}
	
	@GetMapping("/ocorrencia/encerrar/emissor")
	public String concluirOcorrenciaEmissor(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {
		
		//Pega a ocorrência e define a data de conclusão do técnico
		Ocorrencia o = ocorrenciaDAO.buscar(id);
		o.setDataConclusaoEmissor(new Date());
		ocorrenciaDAO.alterar(o);
		
		
		return "redirect:/app";
	}
	
	@PostMapping("/ocorrencia/salvar")
	public String salvar(@Valid Ocorrencia ocorrencia, BindingResult brOcorrencia, Model model, HttpSession session) {
		
		//Aplicando validações
		if(brOcorrencia.hasErrors()) {
			System.out.println(brOcorrencia);
			//Repassa as categorias para reabrir o formulário
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			return "ocorrencia/form";
		}
		
		
		//Persistir no banco de dados e aplicando a data de cadastro para agora
		ocorrencia.setDataModificacao(new Date());
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		
		
		//Verificando se cadastro
		if(ocorrencia.getId() == null) {
			//Obtendo o usuário logado
			
			ocorrencia.setDataCadastro(new Date());
			ocorrencia.setEmissor(autenticado);
			
			ocorrenciaDAO.persistir(ocorrencia);
		}
		//Verifica se alteralçao
		else {
			//Busca a ocorrência do banco de dados para alterá-la
			Ocorrencia ocorrenciaAlterada = ocorrenciaDAO.buscar(ocorrencia.getId());
			
			//SEGURANÇA: VErificar se o usuário logado é o dono da ocorrência...
			if(ocorrenciaAlterada.getEmissor().getId() != autenticado.getId()) {
				throw new RuntimeException("Você não pode modificar esta ocorrência.");
			}
			
//			BeanUtils.copyProperties(ocorrencia, ocorrenciaAlterada, 
//				"id", "emissor", "dataCadastro", "tecnico"); // <- campos que estou ignorando
			
			ocorrenciaAlterada.setTitulo(ocorrencia.getTitulo());
			ocorrenciaAlterada.setDescricao(ocorrencia.getDescricao());
			ocorrenciaAlterada.setCategoria(ocorrencia.getCategoria());
			
			ocorrenciaDAO.alterar(ocorrenciaAlterada);
			
		}
		
		
		
		
		
		
		
		return "redirect:/app";
	}
	

}
