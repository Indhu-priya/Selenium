package testDefinition;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import businessLogics.HomePage;


public class TestDemo {
	
	@Parameters ("browserName")
	@Test
	public void TestCase_one_verifyTestInAllBrowser() {
		HomePage page = new HomePage();
		page.openRocheSite();
	}

}
