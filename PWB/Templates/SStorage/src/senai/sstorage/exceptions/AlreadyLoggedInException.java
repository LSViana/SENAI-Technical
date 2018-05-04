package senai.sstorage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyLoggedInException extends Exception {

	public AlreadyLoggedInException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedInException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedInException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedInException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedInException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
