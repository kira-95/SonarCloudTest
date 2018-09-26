package demo.junit_demo;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import demo.junit_demo.services.BookValidatorService;

@RunWith(Parameterized.class)
public class BookValidatorServiceTest {

	private String bookName;
	private Boolean expectedResult;
	private BookValidatorService bookValidatorService;

	@Before
	public void initialize() {
		bookValidatorService = new BookValidatorService();
		bookValidatorService.setBlackListed(Arrays.asList("JFK", "XYZ"));
	}

	public BookValidatorServiceTest(String bookName, Boolean expectedResult) {
		this.bookName = bookName;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> bookList() {
		return Arrays.asList(new Object[][] {
			{ "ABC", false },
			{ "XYZ", true },
			{ "PQR", false },
			{ "JFK", true },
			{ "1984", false }
		});
	}

	@Test
	public void testPrimeNumberChecker() {
		System.out.println("Parameterized Number is : " + bookName);
		Assert.assertEquals(expectedResult, bookValidatorService.checkBlackListed(bookName));
	}

}
