package notadomain.aeras.exception;

@SuppressWarnings("serial")
public class UnauthorizedAccessException extends Exception {
public UnauthorizedAccessException() {}
	
	public UnauthorizedAccessException(String message) {
		super(message);
	}
	
	public UnauthorizedAccessException(Throwable cause) {
		super(cause);
	}
	
	public UnauthorizedAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
