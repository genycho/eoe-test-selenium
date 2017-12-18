package eoe.selenium.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import eoe.selenium.EoESeleniumTestCase;
import eoe.selenium.GUITestUtils;
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
public class EndUserDetailTest extends EoESeleniumTestCase{
	String testId = GUITestData.TEST_ID;
	String testPw = GUITestData.TEST_PW;
	
	String testUserID = "selenium_detail_userid";
	String testUserPW = "selenium_detail_userpw";
	String testUserEmail = "guitest@eoe.com";
	
	public EndUserDetailTest() {
		super();
//		baseUrl=baseUrl+"/login";
		setNewlyOpenBrowser(false);
		setCloseBrowser(false);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		CommonFunctions.getInstance().login(getDriver(), this.baseUrl, testId, testPw);
		Thread.sleep(3000);
		CommonFunctions.getInstance().moveToUserListPage(getDriver());
		waitForElementPresent(UserPageElementPath.xpathUserTitle);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		CommonFunctions.getInstance().logout(getDriver()); 
	}

	@Test//("수정 테스트에 이미 다 포함되어 있어서 생략 ")
	public void testUserDetail_기본() throws Exception {
		/*	Pre-Condition	*/
		CommonFunctions.getInstance().createEndUser(getDriver(), testUserID, testUserPW, testUserEmail, null);
		Thread.sleep(3000);
		
		/*	In Test - Moving to the detail page	*/
		GUITestUtils.scrollUpToTop(getDriver());
		Thread.sleep(1000);
		getXPathElement(UserPageElementPath.xpathFirstDataEmail).click();
		Thread.sleep(2000);
		waitForElementPresent(UserPageElementPath.xpathUserDetailTitle);
		getNgDriver().waitForAngularRequestsToFinish();
		
		/*	Checking each input fields 'readonly'(for input fields), 'disabled' (for button, controls)  property, readonly fields	*/
		//cannot modify
		
		String textUserId = getXPathElement(UserPageElementPath.xpathUserDetailUserId).getText();
		System.out.println("_detail page: userid = "+textUserId);
		
		String disabled00= getXPathElement(UserPageElementPath.xpathUserDetailUserId).getAttribute("readonly");
		assertNotNull(disabled00);
		assertEquals("true", disabled00);
		String disabled01= getXPathElement(UserPageElementPath.xpathUserDetailAccessedDate).getAttribute("readonly");
		assertNotNull(disabled01);
		assertEquals("true", disabled01);
		String disabled02 = getXPathElement(UserPageElementPath.xpathUserDetailCreatedDate).getAttribute("readonly");
		assertNotNull(disabled02);
		assertEquals("true", disabled02);
		String disabled03 = getXPathElement(UserPageElementPath.xpathUserDetailResetBtn).getAttribute("disabled");//true
		assertNotNull(disabled03);
		assertEquals("true", disabled03);
		String disabled04 = getXPathElement(UserPageElementPath.xpathUserDetailSaveBtn).getAttribute("disabled");
		assertNotNull(disabled04);
		assertEquals("true", disabled04);
		
		//can modify
		String enable01 = getXPathElement(UserPageElementPath.xpathUserDetailAddress).getAttribute("readonly");//null
		assertNull(enable01);
		String enable02 = getXPathElement(UserPageElementPath.xpathUserDetailEmail).getAttribute("readonly");
		assertNull(enable02);
		String enable03 = getXPathElement(UserPageElementPath.xpathUserDetailFirstName).getAttribute("readonly");
		assertNull(enable03);
		String enable04 = getXPathElement(UserPageElementPath.xpathUserDetailLastName).getAttribute("readonly");
		assertNull(enable04);
		String enable05 = getXPathElement(UserPageElementPath.xpathUserDetailMiddleName).getAttribute("readonly");
		assertNull(enable05);
		String enable06 = getXPathElement(UserPageElementPath.xpathUserDetailMobile).getAttribute("readonly");
		assertNull(enable06);
		String enable07 = getXPathElement(UserPageElementPath.xpathUserDetailNickName).getAttribute("readonly");
		assertNull(enable07);
		Thread.sleep(2000);
		
		getXPathElement(UserPageElementPath.xpathGoToUserList).click();
		Thread.sleep(3000);
		getNgDriver().waitForAngularRequestsToFinish();
		
		/*	Clean-up	*/
		GUITestUtils.scrollUpToTop(getDriver());
		CommonFunctions.getInstance().deleteFirstEndUser(getDriver());
		Thread.sleep(2000);
	}
	
}
