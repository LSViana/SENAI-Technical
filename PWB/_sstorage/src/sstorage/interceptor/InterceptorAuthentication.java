package sstorage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import sstorage.models.User;

public class InterceptorAuthentication extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().toString();
		User user = (User)request.getSession().getAttribute(User.USER_KEY);
		Boolean isAuthenticated = user != null;
		// Authentication Condition
		if(!isAuthenticated && url.contains("/app")) {
			response.setStatus(401);
			return false;
		}
		// Admin Condition
		// ...
		// Access Allowed
		return true;
	}

}
