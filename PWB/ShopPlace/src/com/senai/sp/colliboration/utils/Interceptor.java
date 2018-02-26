package com.senai.sp.colliboration.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SessionManager sessionManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean needAuth = request.getRequestURI().contains("/app");
		if(needAuth && !sessionManager.isLoggedIn(request.getSession())) {
			response.setStatus(401);
			return false;
		}
		else {
			return true;
		}
	}

}