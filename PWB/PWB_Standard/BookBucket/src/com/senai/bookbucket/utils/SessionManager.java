package com.senai.bookbucket.utils;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.senai.bookbucket.model.User;

@Component
public class SessionManager {
	
	// Stores the key where usuario is saved at sessions
	public static final String USER_KEY = "user";
	
	// Stores the authenticated user to the HttpSession
	public void setLoggedIn(HttpSession session, User user) {
		session.setAttribute(USER_KEY, user);
	}
	
	// Gets the stored user at the HttpSession
	public User getLoggedInt(HttpSession session) {
		return (User)session.getAttribute(USER_KEY);
	}
	
	public void setLoggedOff(HttpSession session) {
		session.invalidate();
	}

	public boolean isLoggedIn(HttpSession session) {
		return session.getAttribute(USER_KEY) != null;
	}
	
}