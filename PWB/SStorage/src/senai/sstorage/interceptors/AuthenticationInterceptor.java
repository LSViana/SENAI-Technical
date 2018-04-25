package senai.sstorage.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	private static String APP_PREFIX = "/app";
	private static String APP_TITLE = "title", VALUE_TITLE = "SStorage";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Setting custom data
		request.setAttribute(APP_TITLE, VALUE_TITLE);
		// Verifying Permissions
		String uri = request.getRequestURI().toString();
		if(uri.contains(APP_PREFIX)) {
			return false;
		}
		return true;
	}

}
