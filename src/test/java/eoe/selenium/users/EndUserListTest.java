package eoe.selenium.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import eoe.selenium.EoESeleniumTestCase;
import eoe.selenium.common.CommonFunctions;
import eoe.selenium.common.GUITestData;
import eoe.selenium.uielement.LonginPageElementPath;
import eoe.selenium.uielement.UserPageElementPath;

/**
 * EoE Login Test	</br>
 * 
 * 참고 :  https://github.com/paul-hammant/ngWebDriver/blob/master/src/test/java/com/paulhammant/ngwebdriver/AngularAndWebDriverTest.java
 *
 */
public class EndUserListTest extends EoESeleniumTestCase{
	String testId = GUITestData.TEST_ID;
	String testPw = GUITestData.TEST_PW;
	
	public EndUserListTest() {
		super();
//		baseUrl=baseUrl+"/login";
		setNewlyOpenBrowser(false);
		setCloseBrowser(false);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testUserList_기본() throws Exception {
//		Thread.sleep(2000);
		CommonFunctions.getInstance().login(getDriver(), this.baseUrl, testId, testPw);

		Thread.sleep(3000);
		waitForElementPresent(UserPageElementPath.xpathUserTitle);
		
		String totalCount = getXPathElement(UserPageElementPath.xpathUserListCount).getText();
		
		getXPathElement(UserPageElementPath.xpathSearchCondSelect).click();
		Thread.sleep(2000);
		getXPathElement(UserPageElementPath.xpathNickNameCond).click();
		Thread.sleep(2000);
		getXPathElement(UserPageElementPath.xpathSearchKeyword).sendKeys("nodata_search");
		Thread.sleep(2000);
		
		String count = getXPathElement(UserPageElementPath.xpathUserListCount).getText();
		assertEquals("0 total", count);
		
		getXPathElement(UserPageElementPath.xpathSearchKeyword).clear();
		getXPathElement(UserPageElementPath.xpathSearchKeyword).sendKeys(Keys.ENTER);;
		Thread.sleep(4000);
		count = getXPathElement(UserPageElementPath.xpathUserListCount).getText();
		assertEquals(totalCount, count);
		
		/*	컬럼 정렬	*/
		getXPathElement(UserPageElementPath.xpathCreatedColumnHeader).click();
		Thread.sleep(2000);
		getXPathElement(UserPageElementPath.xpathCreatedColumnHeader).click();
		Thread.sleep(2000);
		
		/*	첫번째 건 클릭 이동 */
		String firstUserEmail = getXPathElement(UserPageElementPath.xpathFirstDataEmail).getText();
		String firstUserID = getXPathElement(UserPageElementPath.xpathFirstDataUserId).getText();
		getXPathElement(UserPageElementPath.xpathFirstDataEmail).click();
		Thread.sleep(2000);
		System.out.println("first user email : "+firstUserEmail);
		System.out.println("first user id : "+firstUserID);
		
		waitForElementPresent(UserPageElementPath.xpathUserDetailTitle);
		String userDisplayName = getXPathElement(UserPageElementPath.xpathUserDPName).getText();
		assertEquals(firstUserEmail, userDisplayName);
		
		getXPathElement(UserPageElementPath.xpathGoToUserList).click();
		Thread.sleep(3000);
		waitForElementPresent(UserPageElementPath.xpathUserTitle);
		
		CommonFunctions.getInstance().logout(getDriver()); 
	}
	
}
