package demo.junit_demo.services;

import demo.junit_demo.models.Book;
import demo.junit_demo.models.BookDao;
import exceptions.BlacklistedBookException;
import exceptions.BookNotFoundException;
import exceptions.OutOfStockException;

public class BookService {

	private BookDao bookDao;
	
	private BookValidatorService bookValidator;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setBookValidator(BookValidatorService bookValidator) {
		this.bookValidator = bookValidator;
	}

	public boolean buy(String bookName, Integer numberOfCopies) throws BookNotFoundException, OutOfStockException, BlacklistedBookException {
		boolean transactionStatus = false;
		if(bookValidator.checkBlackListed(bookName)) {
			throw new BlacklistedBookException(bookName + " is black listed");
		}
		
		Book book = bookDao.findBookByName(bookName);
		if(book == null)
		{
			throw new BookNotFoundException(bookName + " not found");
		}
		else
		{
			if (book.getCopies() < numberOfCopies) {
				throw new OutOfStockException(bookName + " is out of stock");
			}
			bookDao.buyBook(book);
		}
		transactionStatus=true;
		return transactionStatus;
	}

}