package br.senai.sp.info.pweb.jucacontrol.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

public class AutenticacaoInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().toString();
		Usuario u = (Usuario)request.getSession().getAttribute("usuarioAutenticado");
		Boolean estaLogado = u != null;
		if(!estaLogado && url.contains("/app")) {
			response.setStatus(401);
			return false;
		}
		if(url.contains("/adm") && !u.isAdministrador()) {
			response.setStatus(401);
			return false;
		}
		return true;
	}

}
