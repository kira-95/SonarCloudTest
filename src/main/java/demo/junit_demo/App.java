package demo.junit_demo;

public class App 
{
	double multiply(double a, double b) {
		return a*b;
	}
	
	double divide(double a, double b) {
		if(b == 0)
			throw new RuntimeException("Cannot divide by 0");
		return a/b;
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
