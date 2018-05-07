package wstemplate.project.utils;

import org.springframework.util.DigestUtils;

public class PasswordUtils {
	
	private PasswordUtils() {
		// Construction not allowed
	}
	
	public static String hashString(String password) {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

}
