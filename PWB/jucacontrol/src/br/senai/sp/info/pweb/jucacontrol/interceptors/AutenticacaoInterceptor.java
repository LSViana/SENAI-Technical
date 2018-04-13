package br.senai.sp.info.pweb.jucacontrol.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutenticacaoInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("usuarioAutenticado") == null && request.getRequestURI().toString().contains("/app")) {
			return false;
		}
		return true;
	}

}
