package br.senai.sp.info.pweb.jucacontrol.controllers;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.jboss.jandex.TypeTarget.Usage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Controller
public class UsuarioController {
	
	public static final String USUARIO_KEY = "usuarioAutenticado";
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping(value = {"/", ""})
	public String abrirLogin(Model model, HttpSession sessao) {
		if(sessao.getAttribute("usuarioAutenticado") != null) {
			return "redirect:/app";
		}
		model.addAttribute("usuario", new Usuario());
		return "index";
	}
	
	@GetMapping("/app/adm/usuario/editar")
	public String abrirEdicao(Model model, @RequestParam(name = "id", required = true) Long id, HttpServletResponse response) throws IOException {
		
		//Buscar o usuário pelo id
		Usuario usuarioBuscado = usuarioDAO.buscar(id);
		model.addAttribute("usuario", usuarioBuscado);
		
		return "usuario/form";
	}
	
	@GetMapping("/app/adm/usuario")
	public String abrirLista(Model model) {
		
		model.addAttribute("usuarios", usuarioDAO.buscarTodos());
		
		return "usuario/lista";
	}
	
	@GetMapping("/app/adm/usuario/novo")
	public String abrirFormNovoUsuario(Model model) {
		
		//Passando o usuário vazio para o model attribute
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/form";
	}
	
	@GetMapping("/app/perfil")
	public String abrirFormEditarUsuarioLogado(Model model) {
		
		return "usuario/form";
	}
	
	@GetMapping("/app/alterarSenha")
	public String abrirFormAlterarSenha(Model model) {
		
		return "usuario/alterarSenha";
	}
	
	@GetMapping("/app/adm/usuario/deletar")
	public String deletar(@RequestParam(required = true) Long id, HttpServletResponse response) throws IOException {
		
		usuarioDAO.deletar(usuarioDAO.buscar(id));
		
		return "redirect:/app/adm/usuario";
	}
	
	@PostMapping( value = {"/app/adm/usuario/salvar"})
	public String salvar(@Valid Usuario usuario, BindingResult brUsuario,
						@RequestParam(name = "confirmacaoSenha", required = false) String confirmacao,
						@RequestParam(name = "administrador", required = false) Boolean isAdministrador) {
		
		//VERIFICANDO SE CADASTRO
		if(usuario.getId() == null) {
			
			//Checando se a confirmação de senha NÃO for igual a senha
			if(!confirmacao.equals(usuario.getSenha())) {
				brUsuario.addError(new FieldError("usuario", "senha", "As senhas não coincidem"));
			}
			
			//Verificando se email já existe
			if(usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
				brUsuario.addError(new FieldError("usuario", "email", "O email '" + usuario.getEmail() + "' já está em uso"));
			}
			
			//Verificando se o binding result contem erros
			if(brUsuario.hasErrors()) {
				return "usuario/form"; //Reabre a tela de cadastro
			}
			
		}
		//VERIFICANDO SE É ALTERAÇÃO
		else {
			if(	brUsuario.hasFieldErrors("nome") || 
				brUsuario.hasFieldErrors("sobrenome")){
				return "usuario/form";
			}
		}		
		
		
		//Verificando se usuário é administrador
		if(isAdministrador != null) {
			usuario.setTipo(TiposUsuario.ADMINISTRADOR);
		}else {
			usuario.setTipo(TiposUsuario.COMUM);
		}
		
		
		//CASO CADASTRO...
		if(usuario.getId() == null) {
			//Armazena o usuário no banco de dados
			usuario.hashearSenha();
			usuarioDAO.persistir(usuario);
		}else {
			
			//Buscar o usuário do banco
			Usuario usuarioBanco = usuarioDAO.buscar(usuario.getId());
			
			//Altera com os dados que recebi do formulário
			usuarioBanco.setNome(usuario.getNome());
			usuarioBanco.setSobrenome(usuario.getSobrenome());
			usuarioBanco.setTipo(usuario.getTipo());
			
			//Altera o usuário do
			usuarioDAO.alterar(usuarioBanco);
		}
		
		return "redirect:/app/adm/usuario";
	}
	
	@PostMapping({"/usuario/autenticar"})
	//@Valid - Determina que o Spring deve validar o objeto
	//BindingResult - Armazena os possíveis erros de validação que ocorreram no objeto
	public String autenticar(@Valid Usuario usuario, BindingResult brUsuario, HttpSession sessao) {
		
		// Verificando se houveram erros no binding result
		if(brUsuario.hasFieldErrors("email") || 
				brUsuario.hasFieldErrors("senha")) {	
			return "index";
		}
		
		usuario.hashearSenha();
		Usuario u = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		if(u == null) {
			// Se o usuário não existir, isto é, se ele não conseguiu se autenticar
			brUsuario.addError(new FieldError("usuario", "email", "E-mail ou senha inválidos!"));
			return "index";
		}
		
		sessao.setAttribute(USUARIO_KEY, u);		
		return "redirect:/app/";
	}
	
	@GetMapping({"/sair"})
	public String logout(HttpSession sessao) {
		sessao.invalidate();
		return "redirect:/";
	}

}
