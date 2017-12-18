package eoe.selenium.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;

public class NaverMovieSearchTest extends NaverSeleniumTestCase {

	static{
		System.setProperty("TARGET_BROWSER", "chrome");
		System.setProperty("SELENIUM_TESTENV", "maclocal");
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		driver = getDriver();
		baseUrl = "http://www.naver.com/";
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void testNaverMovieSearch() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector("a.mn_movie > span")).click();
		driver.findElement(By.id("search_placeholder")).click();
		driver.findElement(By.id("ipt_tx_srch")).click();
		driver.findElement(By.id("ipt_tx_srch")).click();
		driver.findElement(By.id("ipt_tx_srch")).clear();
		driver.findElement(By.id("ipt_tx_srch")).sendKeys("공조");
		driver.findElement(By.cssSelector("button.btn_srch")).click();

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if ("검색결과 : 네이버 영화".equals(driver.getTitle()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if ("영화".equals(driver.findElement(By.cssSelector("h3")).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		assertEquals("공조", driver.findElement(By.cssSelector("dt > a > strong")).getText());
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.linkText("공조 (共助)")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		String resultMovieName = driver.findElement(By.cssSelector("dt > a > strong")).getText();
		assertEquals("공조", resultMovieName);

		driver.findElement(By.cssSelector("img[alt=\"NAVER\"]")).click();

	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}

