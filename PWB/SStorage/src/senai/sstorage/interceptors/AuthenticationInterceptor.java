package senai.sstorage.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import senai.sstorage.models.User;
import senai.sstorage.utils.SessionUtils;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	private static String APP_PREFIX = "/app";
	private static String ADM_PREFIX = "/adm";
	private static String APP_TITLE = "title", VALUE_TITLE = "SStorage";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Setting Custom Data
		request.setAttribute(APP_TITLE, VALUE_TITLE);
		String uri = request.getRequestURI().toString();
		Object objUser = request.getSession().getAttribute(SessionUtils.USER_KEY);
		// Verifying Regular Permissions
		if(uri.contains(APP_PREFIX)) {
			if(objUser instanceof User) {
				return true;
			}
			return false;
		}
		// Verifying Administrator Access
		if(uri.contains(ADM_PREFIX)) {
			if(objUser instanceof User) {
				User user = (User) objUser;
				if(user.isAdministrator())
					return true;
			}
			return false;
		}
		return true;
	}

}
