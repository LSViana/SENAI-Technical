package senai.sstorage.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class WebUtils {

	public Map<String, String> mapOf(BindingResult br) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError fe : br.getFieldErrors()) {
			errors.put(fe.getField(), fe.getDefaultMessage());
		}
		return errors;
	}

	@Autowired
	private ServletContext context;

	public URI getUri(String path) throws URISyntaxException {
		return new URI(context.getContextPath() + path);
	}

}
