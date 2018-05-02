package todo.exceptions;

/**
 * Classe destinada a erros relativos a validação de entidades em regras de negócio
 * @author Felipe Oliveira
 *
 */
public class ValidacaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidacaoException() {
		super();
	}

	public ValidacaoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ValidacaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ValidacaoException(String arg0) {
		super(arg0);
	}

	public ValidacaoException(Throwable arg0) {
		super(arg0);
	}
	
	
	

}
