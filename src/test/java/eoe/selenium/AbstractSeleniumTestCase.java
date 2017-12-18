package eoe.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;

public abstract class AbstractSeleniumTestCase {
	public static final int BROWSER_CHROME = 1;
	public static final int BROWSER_FIREFOX = 2;
	public static final int BROWSER_INTERNETEXPLORER = 3;

	public static final int TESTENV_LOCAL = 1;
	public static final int TESTENV_DEV = 2;
	public static final int TESTENV_TEST = 3;
	public static final int TESTENV_PROD = 4;
	
	public static final int TESTENV_MACLOCAL = 5;

	private static int targetBrowser = BROWSER_CHROME;
	private static int testEnv = TESTENV_DEV;

	private static boolean isRead = false;
	
	protected String baseUrl = null;
	protected WebDriver driver = null;
	protected NgWebDriver ngDriver = null;
	private boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();
	
	private boolean newlyOpenBrowser = true;
	private boolean isCloseBrowser = true;

	public void readTestEnvValues() {
		if (isRead == false) {
			isRead = true;
			String selectedTargetBrowser = System.getProperty("TARGET_BROWSER");
			String testEnvVar = System.getProperty("SELENIUM_TESTENV");

			if (selectedTargetBrowser == null || testEnvVar == null) {
				System.out.println(
						"Check if you set the required Sytem variables ('TARGET_BROWSER' or 'SELENIUM_TESTENV'), They will be set as the default value. "
								+ selectedTargetBrowser + ", " + testEnvVar);
			}

			if ("firefox".equalsIgnoreCase(selectedTargetBrowser)) {
				targetBrowser = BROWSER_FIREFOX;
			} else if ("chrome".equalsIgnoreCase(selectedTargetBrowser)) {
				targetBrowser = BROWSER_CHROME;
			} else if ("ie".equalsIgnoreCase(selectedTargetBrowser)) {
				targetBrowser = BROWSER_INTERNETEXPLORER;
			} else {
				System.out.println(
						"Not supported browser type(should be in (firefox / chrome / ie ) - " + selectedTargetBrowser);
//				throw new GUITestRunimeException 할까 디폴트값으로 설정할까...
			}

			if ("local".equalsIgnoreCase(testEnvVar)) {
				testEnv = TESTENV_LOCAL;

				System.setProperty("webdriver.ie.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_iedriver_32/IEDriverServer.exe");
				System.setProperty("webdriver.chrome.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_chromedriver_32/chromedriver.exe");
				System.setProperty("webdriver.gecko.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_firefoxdriver_32/geckodriver.exe");

			} else if ("dev".equalsIgnoreCase(testEnvVar)) {
				testEnv = TESTENV_DEV;

				System.setProperty("webdriver.ie.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_iedriver_32/IEDriverServer.exe");
				System.setProperty("webdriver.chrome.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_chromedriver_32/chromedriver.exe");
				System.setProperty("webdriver.gecko.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_firefoxdriver_32/geckodriver.exe");

			} else if ("test".equalsIgnoreCase(testEnvVar)) {
				testEnv = TESTENV_TEST;

				throw new GUITestRunimeException("Not yet supported test env - " + testEnvVar);

			} else if ("prod".equalsIgnoreCase(testEnvVar)) {
				testEnv = TESTENV_PROD;

				throw new GUITestRunimeException("Not yet supported test env - " + testEnvVar);

			} else if ("maclocal".equalsIgnoreCase(testEnvVar)) {
				testEnv = TESTENV_MACLOCAL;

				System.setProperty("webdriver.ie.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_iedriver_32/IEDriverServer");
				System.setProperty("webdriver.chrome.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/macos_chromedriver/chromedriver");
				System.setProperty("webdriver.gecko.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/macos_firefoxdriver/geckodriver/geckodriver");

			}else {
				testEnv = TESTENV_DEV;
				System.out.println("Not supported test environment value(should be in ( local / dev / test / prod / maclocal) - "
						+ testEnvVar);
				System.out.println("will be set as the defaul value - " + testEnvVar);
				
				System.setProperty("webdriver.ie.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_iedriver_32/IEDriverServer.exe");
				System.setProperty("webdriver.chrome.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_chromedriver_32/chromedriver.exe");
				System.setProperty("webdriver.gecko.driver",
						GUITestUtils.getProjectPath() + "/webdrivers/windows_firefoxdriver_32/geckodriver.exe");

			}
		} else {
			System.out.println("Test Env is not defined. The default env is set.");
			// do nothing
		}
		// isRead = true;
	}

	public int getTestEnv() {
		return testEnv;
	}

	public boolean isRead() {
		return isRead;
	}

	/**
	 * This will set the basic test requirements like - base url, WebDriver and NgWebDriver, etc
	 */
	public void setBasicEnv() {
		switch (getTestEnv()) {
		case AbstractSeleniumTestCase.TESTENV_LOCAL:
			baseUrl = "http://10.250.46.35:4200";
			break;
		case AbstractSeleniumTestCase.TESTENV_DEV:
			baseUrl = "https://eoe-portal.firebaseapp.com";
			break;
		default:
			baseUrl = "";
			break;
		}

		if (this.driver == null) {
			System.out.println("Checking the request target browser value - " +AbstractSeleniumTestCase.targetBrowser);
			switch (AbstractSeleniumTestCase.targetBrowser) {
			case BROWSER_CHROME:
				ChromeDriver chromeDriver = new ChromeDriver();
				this.driver = chromeDriver;
				this.ngDriver = new NgWebDriver(chromeDriver);
				break;
			case BROWSER_FIREFOX:
				FirefoxDriver firefoxDriver = new FirefoxDriver();
				this.driver = firefoxDriver;
				this.ngDriver = new NgWebDriver(firefoxDriver);
				break;
			case BROWSER_INTERNETEXPLORER:
				InternetExplorerDriver idDriver = new InternetExplorerDriver();
				this.driver = idDriver;
				this.ngDriver = new NgWebDriver(idDriver);
				break;
			default:
				ChromeDriver webDriver = new ChromeDriver();
				this.driver = webDriver;
				this.ngDriver = new NgWebDriver(webDriver);
			}
		}
	}
	
	/**
	 * This will reset the basic test requirements as null </br>
	 * refer to {{@link #setBasicEnv()}
	 */
	public void resetBasicEnv() {
		this.driver = null;
		this.ngDriver = null;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public NgWebDriver getNgDriver() {
		return ngDriver;
	}

	protected void waitForElementPresent(String xpathKeyword) throws InterruptedException {
		GUITestUtils.waitForElementPresent(driver, xpathKeyword);
	}
	
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
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
	
	public WebElement getIDElement(String findKeyword) {
		return GUITestUtils.getIDElement(driver, findKeyword);
	}
	
	public WebElement getXPathElement(String findKeyword) {
		return GUITestUtils.getXPathElement(driver, findKeyword);
	}
	
	public WebElement getLinkTextElement(String findKeyword) {
		return GUITestUtils.getLinkTextElement(driver, findKeyword);
	}
	
	public WebElement getNgButtonTxt(String buttonText) {
		return GUITestUtils.getNgButtonTxt(driver, buttonText);
	}

	public boolean isNewlyOpenBrowser() {
		return newlyOpenBrowser;
	}

	public void setNewlyOpenBrowser(boolean newlyOpenBrowser) {
		this.newlyOpenBrowser = newlyOpenBrowser;
	}

	public boolean isCloseBrowser() {
		return isCloseBrowser;
	}

	public void setCloseBrowser(boolean isCloseBrowser) {
		this.isCloseBrowser = isCloseBrowser;
	}
}
