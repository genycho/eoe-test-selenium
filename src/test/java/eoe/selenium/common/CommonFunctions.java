package eoe.selenium.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.NgWebDriver;

import eoe.selenium.GUITestException;
import eoe.selenium.GUITestUtils;
import eoe.selenium.uielement.LonginPageElementPath;
import eoe.selenium.uielement.UserPageElementPath;

public class CommonFunctions{
	private static CommonFunctions instance;
	
	protected String baseUrl = null;
	protected WebDriver driver = null;
	protected NgWebDriver ngDriver = null;
	
	private CommonFunctions() {
	}
	
	public static CommonFunctions getInstance() {
		if(instance == null) {
			instance = new CommonFunctions();
		}
		return instance;
	}
	
	public void login(WebDriver webDriver, String baseUrl, String loginId, String loginPw) throws InterruptedException, GUITestException {
		if(GUITestUtils.getXPathElement(webDriver, LonginPageElementPath.xpathLoginArea)==null) {
			throw new GUITestException("There is no Login area. Failed to login");
		}
		
		GUITestUtils.getXPathElement(webDriver, LonginPageElementPath.xpathId).clear();
		GUITestUtils.getXPathElement(webDriver, LonginPageElementPath.xpathId).sendKeys(loginId);
		GUITestUtils.getXPathElement(webDriver, LonginPageElementPath.xpathPw).clear();
		GUITestUtils.getXPathElement(webDriver, LonginPageElementPath.xpathPw).sendKeys(loginPw);
		GUITestUtils.getNgButtonTxt(webDriver,"Login").click();
		GUITestUtils.waitForElementPresent(webDriver, LonginPageElementPath.xpathEoETitle);
	}
	
	/**
	 * 
	 * @param webDriver
	 * @throws InterruptedException
	 */
	public void logout(WebDriver webDriver) throws InterruptedException {
		GUITestUtils.getXPathElement(webDriver, LonginPageElementPath.xpathLogout).click();
		GUITestUtils.waitForElementPresent(webDriver, LonginPageElementPath.xpathLoginArea);
	}
	
	/**
	 * 
	 * @param webDriver
	 * @param userEmailText
	 * @throws GUITestException
	 * @throws InterruptedException
	 */
	public void searchAUserWithEmailInUserList(WebDriver webDriver, String userEmailText) throws GUITestException, InterruptedException {
		((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, 0);");
		
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathSearchCondSelect).click();
		Thread.sleep(2000);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathEmailCond).click();
		Thread.sleep(2000);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathSearchKeyword).sendKeys(userEmailText);
		Thread.sleep(5000);
		
		String count = GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserListCount).getText();
		int searchCount = -1;
		try {
			searchCount = Integer.valueOf(count.split(" ")[0].trim());
		}catch(Throwable failedToParseCounts) {
			throw new GUITestException("Failed to parse the count in UserList", failedToParseCounts);
		}
		if(searchCount <1) {
			throw new GUITestException("There is no user item!!, cannot move to the User detail for - " + userEmailText);
		}
	}
	
	/**
	 * 
	 * @param webDriver
	 * @param userEmailText
	 * @throws InterruptedException
	 * @throws GUITestException
	 */
	public void moveToUserDetail(WebDriver webDriver, String userEmailText) throws InterruptedException, GUITestException {
		Thread.sleep(3000);
		GUITestUtils.waitForElementPresent(webDriver, UserPageElementPath.xpathUserTitle);
		
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserMenu).click();
		Thread.sleep(2000);
		if(userEmailText !=null) {
			((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, 0);");
			GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathSearchCondSelect).click();
			Thread.sleep(2000);
			GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathEmailCond).click();
			Thread.sleep(2000);
			GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathSearchKeyword).sendKeys(userEmailText);
			Thread.sleep(5000);
		}
		
		String count = GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserListCount).getText();
		int searchCount = -1;
		try {
			searchCount = Integer.valueOf(count.split(" ")[0].trim());
		}catch(Throwable failedToParseCounts) {
			throw new GUITestException("Failed to parse the count in UserList", failedToParseCounts);
		}
		if(searchCount <1) {
			throw new GUITestException("There is no user item!!, cannot move to the User detail for - " + userEmailText);
		}
		
		/*	첫번째 건 클릭 이동 */
//		String firstUserEmail = GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathFirstDataEmail).getText();
//		String firstUserID = GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathFirstDataUserId).getText();
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathFirstDataEmail).click();
		Thread.sleep(2000);
		
		GUITestUtils.waitForElementPresent(webDriver, UserPageElementPath.xpathUserDetailTitle);
	}
	
	public void moveToUserListPage(WebDriver webDriver) throws GUITestException, InterruptedException {
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserMenu).click();
		Thread.sleep(2000);
		GUITestUtils.waitForElementPresent(webDriver, UserPageElementPath.xpathUserTitle);
	}
	
	
	public void createEndUser(WebDriver webDriver, String endUserId, String endUserPw, String endUserEmail, String appName) throws InterruptedException {
		GUITestUtils.scrollDownToBottom(webDriver);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathOpenCreateUserBtn).click();
		Thread.sleep(2000);
		GUITestUtils.waitForElementPresent(webDriver, UserPageElementPath.xpathCreateUserPopupTitle);
		Thread.sleep(3000);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathAppSelect).click();
		Thread.sleep(3000);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathAppFirstItem).click();
		Thread.sleep(1000);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathFirstNameInput).sendKeys("selenium");
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathMidNameInput).sendKeys("test");
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathLastNameInput).sendKeys("user");
		
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathEmailInput).sendKeys(endUserEmail);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserIDInput).sendKeys(endUserId);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserPWInput).sendKeys(endUserPw);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathUserPWConfirmInput).sendKeys(endUserPw);
//		getXPathElement(UserPageElementPath.xpathUserPWConfirmInput).sendKeys(this.testPw);
		Thread.sleep(1000);

		GUITestUtils.getNgButtonTxt(webDriver, "Save").click();
		Thread.sleep(3000);
	}

	
	public void deleteFirstEndUser(WebDriver webDriver) throws InterruptedException {
		((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, 0);");
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathFirstDataDeleteBtn).click();
		Thread.sleep(2000);
		GUITestUtils.getXPathElement(webDriver, UserPageElementPath.xpathDeleteConfirmOKBtn).click();
		Thread.sleep(3000);
	}

}
