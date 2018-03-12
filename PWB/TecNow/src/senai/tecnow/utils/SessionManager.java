package senai.tecnow.utils;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import senai.tecnow.model.User;

@Component
public class SessionManager {
	
	public static final String USER_KEY = "user";
	
	public void setLoggedIn(HttpSession session, User user) {
		session.setAttribute(USER_KEY, user);
	}
	
	public User getLoggedIn(HttpSession session) {
		return (User)session.getAttribute(USER_KEY);
	}
	
	public void setLoggedOff(HttpSession session) {
		session.invalidate();
	}
	
	public boolean isLoggedIn(HttpSession session) {
		return session.getAttribute(USER_KEY) != null;
	}
	
}
