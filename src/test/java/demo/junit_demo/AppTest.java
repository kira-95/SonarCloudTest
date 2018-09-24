package demo.junit_demo;

import org.junit.Assert;
import org.junit.Test;

public class AppTest
{
	@Test
    public void multipcationTest() {
        App tester = new App();
        //Expected, Actual
        Assert.assertEquals("10 x 0 must be 0", 0, tester.multiply(10, 0), 0.1);
        Assert.assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10), 0.1);
        Assert.assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0), 0.1);
    }
	
	@Test
    public void divisionTest() {
        App tester = new App();
        //Expected, Actual
        Assert.assertEquals("100 / 10 must be 10", 10, tester.divide(100, 10), 0.1);
    }
	
	//https://www.eclemma.org/faq.html#trouble05
	@Test(expected = RuntimeException.class)
    public void divisionTestDivideBy0() {
        App tester = new App();
        tester.divide(100, 0);
    }
}
