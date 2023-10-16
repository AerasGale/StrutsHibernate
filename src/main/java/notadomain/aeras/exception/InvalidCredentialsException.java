package notadomain.aeras.exception;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends UnauthorizedAccessException {
	public InvalidCredentialsException() {}
	
	public InvalidCredentialsException(String message) {
		super(message);
	}
	
	public InvalidCredentialsException(Throwable cause) {
		super(cause);
	}
	
	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}
