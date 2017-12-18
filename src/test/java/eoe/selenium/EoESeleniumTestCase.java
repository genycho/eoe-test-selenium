package eoe.selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

public abstract class EoESeleniumTestCase extends AbstractSeleniumTestCase {
	static {
//		System.setProperty("SELENIUM_TESTENV", "DEV");
//		System.setProperty("TARGET_BROWSER", "chrome");
		System.setProperty("TARGET_BROWSER", "firefox");
//		System.setProperty("TARGET_BROWSER", "ie");
		// Local로 세팅하고 테스트하는 방법
		// 1) 테스트 실행 시 Java arguments에 -DSELENIUM_TESTENV = LOCAL 설정
		// 2) 위의 static 내 변수 주석 해제 후 직접 작성
		// 3) 상위 EoEAPITestCase의 디폴트 값을 현재 DEV 에서 LOCAL로 변경
	}

	public EoESeleniumTestCase() {
		super.readTestEnvValues();
		setBasicEnv();
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		if(isNewlyOpenBrowser() || ! baseUrl.equals(driver.getCurrentUrl())) {
			driver.manage().window().maximize() ;
			driver.get(baseUrl);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			ngDriver.waitForAngularRequestsToFinish();
		}
	}

	@After
	public void tearDown() throws Exception {
		if(isCloseBrowser()) {
			driver.quit();
		}
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
	
//	driver.findElement(ByAngular.buttonText("Login"));//pk
//	driver.findElement(ByAngular.exactBinding("id"));//binding("id"));
//	driver.findElement(ByAngular.cssContainingText(cssSelector, searchText));


}
