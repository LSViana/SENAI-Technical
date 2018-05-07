package wstemplate.project.utils;

import javax.servlet.http.HttpSession;

import wstemplate.project.models.User;

public class SessionUtils {
	
	public static String USER_KEY = "user_auth";
	
	public static void setLoggedIn(HttpSession session, User user) {
		session.setAttribute(USER_KEY, user);
	}
	
	public static void setLoggedOut(HttpSession session) {
		session.removeAttribute(USER_KEY);
		session.invalidate();
	}
	
	public static Boolean isLoggedIn(HttpSession session) {
		return session.getAttribute(USER_KEY) != null;
	}

}
