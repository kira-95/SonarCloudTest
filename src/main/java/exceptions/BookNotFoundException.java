package exceptions;

public class BookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = null;
	public BookNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
