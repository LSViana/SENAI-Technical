package senai.tecnow.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptor extends HandlerInterceptorAdapter {
	
	public static final String PROFILE_PREFIX = "/profile";
	public static final String APP_PREFIX = "/app";
	
	@Autowired
	private SessionManager sessionManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		boolean needAuth =
				uri.contains(APP_PREFIX) ||
				uri.contains(PROFILE_PREFIX);
		//
		if(needAuth && !sessionManager.isLoggedIn(request.getSession())) {
			response.setStatus(401);
			request.getRequestDispatcher("/errors").forward(request, response);
			return false;
		}
		else {
			return true;
		}
	}

}
