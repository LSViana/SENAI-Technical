package wstemplate.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class NotAllowedException extends Exception {

	public NotAllowedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotAllowedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotAllowedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotAllowedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}	

}
