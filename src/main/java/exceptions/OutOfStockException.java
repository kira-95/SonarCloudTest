package exceptions;

public class OutOfStockException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message = null;
	public OutOfStockException(String message) {
		super(message);
		this.message = message;
	}
}
