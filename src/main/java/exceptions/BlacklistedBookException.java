package exceptions;

public class BlacklistedBookException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = null;
	public BlacklistedBookException(String message) {
		super(message);
		this.message = message;
	}
}
