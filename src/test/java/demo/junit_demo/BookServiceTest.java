package demo.junit_demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import demo.junit_demo.models.Book;
import demo.junit_demo.models.BookDao;
import demo.junit_demo.services.BookService;
import exceptions.BookNotFoundException;
import exceptions.OutOfStockException;

public class BookServiceTest {

	private BookService bookService;

	private BookDao bookDao;
	
	@Before
	public void setupMock() {
		bookDao = Mockito.mock(BookDao.class);
		bookService = new BookService();
		bookService.setBookDao(bookDao);
	}

	@Test
	public void testBuy() throws BookNotFoundException, OutOfStockException {
		Book book = new Book();
		book.setCopies(2);
		Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(book);
		Assert.assertEquals(true, bookService.buy("Lord of the Rings"));
	}

	@Test(expected = BookNotFoundException.class)
	public void testBuy_BookNotFound() throws BookNotFoundException, OutOfStockException {
		Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(null);
		bookService.buy("Lord of the Rings");
	}
	
	@Test(expected = OutOfStockException.class)
	public void testBuy_OutOfStock() throws BookNotFoundException, OutOfStockException {
		Book book = new Book();
		book.setCopies(0);
		Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(book);
		try {
			bookService.buy("Lord of the Rings");
        } catch (OutOfStockException e) {
            Mockito.verify(bookDao, Mockito.times(0)).buyBook(book);
            throw e;
        }
	}
}
