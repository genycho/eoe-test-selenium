package eoe.selenium.sample;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import eoe.selenium.AbstractSeleniumTestCase;

public class NaverSeleniumTestCase extends AbstractSeleniumTestCase {
	WebDriver driver;
	String baseUrl;
	boolean acceptNextAlert = true;
	StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		readTestEnvValues();
	}
	

}
