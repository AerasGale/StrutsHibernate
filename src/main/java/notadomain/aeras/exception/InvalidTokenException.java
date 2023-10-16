package notadomain.aeras.exception;

@SuppressWarnings("serial")
public class InvalidTokenException extends UnauthorizedAccessException {
	public InvalidTokenException() {
		super();
	}
	
	public InvalidTokenException(String message) {
		super(message);
	}
	public InvalidTokenException(Throwable cause) {
		super(cause);
	}
	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
