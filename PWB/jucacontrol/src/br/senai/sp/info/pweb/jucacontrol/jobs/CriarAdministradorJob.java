package br.senai.sp.info.pweb.jucacontrol.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Component
public class CriarAdministradorJob implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		System.out.println("[JOB]: Criação de Administrador");
		
		//Criando objeto do usuário administrador padrão
		Usuario admin = new Usuario();
		admin.setEmail("admin@email.com");
		admin.setNome("Administrador");
		admin.setSenha("admin");
		admin.setSobrenome("do Sistema");
		admin.setTipo(TiposUsuario.ADMINISTRADOR);
		admin.hashearSenha();
		
		System.out.println("[JOB] Verificando existência no usuário administrador...");
		if(usuarioDAO.buscarPorEmail(admin.getEmail()) == null) {
			
			System.out.println("[JOB]: Criando usuário administrador...");
			usuarioDAO.persistir(admin);
			
		}else {
			System.out.println("[JOB]: Administrador já existe.");
		}
		
		
		System.out.println("[JOB]: Usuário administrador pronto para uso.");
	}

}
