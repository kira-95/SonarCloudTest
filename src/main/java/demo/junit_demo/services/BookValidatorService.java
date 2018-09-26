package demo.junit_demo.services;

import java.util.List;

public class BookValidatorService {
	
	List<String> blackListed = null;
	
	public BookValidatorService() {
		//Connect to some govt service, get list of books and assign to allowedBooks
	}
	
	public List<String> getBlackListed() {
		return blackListed;
	}


	public void setBlackListed(List<String> blackListed) {
		this.blackListed = blackListed;
	}

	public Boolean checkBlackListed(String bookName) {
		if(blackListed.contains(bookName))
			return true;
		return false;
	}

}
