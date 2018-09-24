package demo.junit_demo.services;

import demo.junit_demo.models.Book;
import demo.junit_demo.models.BookDao;
import exceptions.BookNotFoundException;
import exceptions.OutOfStockException;

public class BookService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public boolean buy(String bookName) throws BookNotFoundException, OutOfStockException {
		boolean transactionStatus = false;
		Book book = bookDao.findBookByName(bookName);
		if(book == null)
		{
			throw new BookNotFoundException();
		}
		else
		{
			if (book.getCopies() == 0) {
				throw new OutOfStockException();
			}
			bookDao.buyBook(book);
		}
		transactionStatus=true;
		return transactionStatus;
	}

}