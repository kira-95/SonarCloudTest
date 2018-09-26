package demo.junit_demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import demo.junit_demo.models.Book;
import demo.junit_demo.models.BookDao;
import demo.junit_demo.services.BookService;
import demo.junit_demo.services.BookValidatorService;
import exceptions.BlacklistedBookException;
import exceptions.BookNotFoundException;
import exceptions.OutOfStockException;

public class BookServiceTest {

	private BookService bookService;

	private BookDao bookDao;
	
	private BookValidatorService bookValidator;
	
	@Before
	public void setupMock() {
		bookDao = Mockito.mock(BookDao.class);
		bookValidator = Mockito.mock(BookValidatorService.class);
		bookService = new BookService();
		bookService.setBookDao(bookDao);
		bookService.setBookValidator(bookValidator);
	}

	@Test
	public void testBuy() throws BookNotFoundException, OutOfStockException, BlacklistedBookException {
		Book book = new Book();
		book.setName("Lord of the Rings");
		book.setCopies(2);
		
		Mockito.when(bookValidator.checkBlackListed(Mockito.anyString())).thenReturn(false);
		Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(book);
		Assert.assertEquals(true, bookService.buy("Lord of the Rings", 2));
	}
	
	@Test(expected = BlacklistedBookException.class)
	public void testBuy_BookBlacklisted() throws BookNotFoundException, OutOfStockException, BlacklistedBookException {
		Book book = new Book();
		book.setName("Lord of the Rings");
		book.setCopies(2);
		
		Mockito.when(bookValidator.checkBlackListed(Mockito.anyString())).thenReturn(true);
		//Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(book);
		bookService.buy("Lord of the Rings", 2);
	}

	@Test(expected = BookNotFoundException.class)
	public void testBuy_BookNotFound() throws BookNotFoundException, OutOfStockException, BlacklistedBookException {
		Mockito.when(bookValidator.checkBlackListed(Mockito.anyString())).thenReturn(false);
		Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(null);
		bookService.buy("Lord of the Rings", 2);
	}
	
	@Test(expected = OutOfStockException.class)
	public void testBuy_OutOfStock() throws BookNotFoundException, OutOfStockException, BlacklistedBookException {
		Book book = new Book();
		book.setCopies(2);
		
		Mockito.when(bookValidator.checkBlackListed(Mockito.anyString())).thenReturn(false);
		Mockito.when(bookDao.findBookByName(Mockito.anyString())).thenReturn(book);
		try {
			bookService.buy("Lord of the Rings", 3);
        } catch (OutOfStockException e) {
            Mockito.verify(bookDao, Mockito.times(0)).buyBook(book);
            throw e;
        }
	}
}
