package senai.sstorage.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import senai.sstorage.authentication.JwtManager;
import senai.sstorage.dao.UserDAO;
import senai.sstorage.models.User;

@Component
public class JwtFilter extends GenericFilterBean {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		String token = httpReq.getHeader("Authorization");
		
		if(token != null) {
			// Token supplied
			if(token.startsWith("Bearer ")) {
				token = token.split(" ")[1];
				try {
					// Get user from token
					User user = userDAO.search(JwtManager.getUserIdFromToken(token));
					SecurityContextHolder.getContext().setAuthentication(user);
					// Set user to Spring Security Context
				} catch (Exception e) {
					httpRes.setStatus(401);
				}
			} else {
				// Invalid token format
			}
		} else {
			// Token not supplied
		}
		
		chain.doFilter(request, response);
	}

}
