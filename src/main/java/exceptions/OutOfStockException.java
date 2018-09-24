package exceptions;

public class OutOfStockException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message = null;
	public OutOfStockException() { super(); }
	public OutOfStockException(String message) {
		super(message);
		this.message = message;
	}
	public OutOfStockException(Throwable cause)
	{
		super(cause);
	}
	@Override
	public String toString() {
		return message;
	}
}
