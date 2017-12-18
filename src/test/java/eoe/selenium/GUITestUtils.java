package eoe.selenium;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.ByAngular;

public class GUITestUtils {

	public static String getProjectPath(){
		return new File("").getAbsolutePath();
	}
	
	public static String getUniqueString() {
		long millis = System.currentTimeMillis();
		return String.valueOf(millis);
	}
	
	public static void waitFor(long miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void errorScreenShot(WebDriver driver, String imgAbsPath) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(imgAbsPath));
	}
	
	public static WebElement getIDElement(WebDriver driver, String findKeyword) {
		WebElement webElement = driver.findElement(By.id(findKeyword));
		return webElement;
	}
	
	public  static WebElement getXPathElement(WebDriver driver, String findKeyword) {
		WebElement webElement = driver.findElement(By.xpath(findKeyword));
		return webElement;
	}
	
	public  static WebElement getLinkTextElement(WebDriver driver, String findKeyword) {
		WebElement webElement = driver.findElement(By.xpath(findKeyword));
		return webElement;
	}
	
	public  static WebElement getNgButtonTxt(WebDriver driver, String buttonText) {
		return driver.findElement(ByAngular.buttonText(buttonText));
	}
	
	public static void waitForElementPresent(WebDriver driver, String xpathKeyword) throws InterruptedException {
		Thread.sleep(2000);
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (getXPathElement(driver, xpathKeyword) != null)
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public static void scrollDownToBottom(WebDriver webDriver) {
		((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static void scrollUpToTop(WebDriver webDriver) {
		((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, 0);");
	}
}
