package com.senai.sp.sstorage.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.senai.sp.sstorage.models.User;

public class InterceptorAuthentication extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().toString();
		User user = (User)request.getSession().getAttribute(User.USER_KEY);
		Boolean isAuthenticated = user != null;
		// Verifying only User login condition
		if(!isAuthenticated && url.contains("/app")) {
			// Unauthorized
			response.setStatus(401);
			return false;
		}
		// Access allowed
		return true;
	}

}
