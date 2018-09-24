package demo.junit_demo.models;

public interface BookDao {
	
	Book findBookByName(String name);
	
	int buyBook(Book book);
	
	//Book DAO implementation is missing :
	//It can be a Spring DB connection talking to a DB or communicating with a web service hosting a cloud-based repository

}
