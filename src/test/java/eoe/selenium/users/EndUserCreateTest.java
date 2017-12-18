package eoe.selenium.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
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
public class EndUserCreateTest extends EoESeleniumTestCase{
	String testId = GUITestData.TEST_ID;
	String testPw = GUITestData.TEST_PW;
	
	String testUserID = GUITestData.NEW_USERID;
	String testUserPW = GUITestData.NEW_USERPW;
	String testUserEmail = GUITestData.NEW_USEREMAIL;
	
	public EndUserCreateTest() {
		super();
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

	@Test
	public void testUserCreate_기본() throws Exception {
		GUITestUtils.scrollDownToBottom(getDriver());
		getXPathElement(UserPageElementPath.xpathOpenCreateUserBtn).click();
		Thread.sleep(2000);
		/*	컬럼 정렬	*/
		waitForElementPresent(UserPageElementPath.xpathCreateUserPopupTitle);
		
//		String saveBtnDisabled = getXPathElement(UserPageElementPath.xpathUserCreateSaveBtn).getAttribute("disabled");
//		assertNotNull("미입력 상태에서 save 버튼이 비활성화되어 있지 않습니다", saveBtnDisabled);
//		assertEquals("미입력 상태에서 save 버튼이 비활성화되어 있지 않습니다", "disabled", saveBtnDisabled);
		
		Thread.sleep(2000);
		getXPathElement(UserPageElementPath.xpathAppSelect).click();
		Thread.sleep(3000);
		getXPathElement(UserPageElementPath.xpathAppFirstItem).click();
		Thread.sleep(1000);
		getXPathElement(UserPageElementPath.xpathFirstNameInput).sendKeys("selenium");
		getXPathElement(UserPageElementPath.xpathMidNameInput).sendKeys("test");
		getXPathElement(UserPageElementPath.xpathLastNameInput).sendKeys("user");
		
		getXPathElement(UserPageElementPath.xpathEmailInput).sendKeys(this.testUserEmail);
		getXPathElement(UserPageElementPath.xpathUserIDInput).sendKeys(this.testUserID);
		getXPathElement(UserPageElementPath.xpathUserPWInput).sendKeys(this.testPw);
		getXPathElement(UserPageElementPath.xpathUserPWConfirmInput).sendKeys(this.testPw);
		Thread.sleep(1000);

		getNgButtonTxt("Save").click();
		Thread.sleep(1000);//자동닫힘이 됨? 되지 말아야 함? 
		//getXPathElement(UserPageElementPath.xpathGoToUserList).click();
//		waitForElementPresent(UserPageElementPath.xpathUserCreatedMessage);
//		String createdMessage = getXPathElement(UserPageElementPath.xpathUserCreatedMessage).getText();
//		assertEquals("SUCCESS", createdMessage);
		
		Thread.sleep(8000);
		//현재 검색 기능이 정상동작하지 않아 조건 검색 후 확인 동작 불가
//		CommonFunctions.getInstance().searchAUserWithEmailInUserList(getDriver(), this.testUserEmail);
//		Thread.sleep(3000);
		GUITestUtils.scrollUpToTop(getDriver());
		Thread.sleep(1000);
		getXPathElement(UserPageElementPath.xpathFirstDataDeleteBtn).click();
		Thread.sleep(2000);
		getXPathElement(UserPageElementPath.xpathDeleteConfirmOKBtn).click();
		Thread.sleep(3000);
		CommonFunctions.getInstance().logout(getDriver()); 
	}
	
	@Test
	public void testUserCreate_같은아이디중복등록() throws Exception {
		CommonFunctions.getInstance().createEndUser(getDriver(), "selenium_sameid", this.testUserPW, "first@email.com", null);
		Thread.sleep(5000);
		CommonFunctions.getInstance().createEndUser(getDriver(), "selenium_sameid", this.testUserPW, "second@email.com", null);
		
		String errorMessage = getXPathElement(UserPageElementPath.xpathUserCreateErrorMessageArea).getText();
		if(errorMessage ==null) {
			verificationErrors.append("Error message is null!!! ");
		}
		if(!"Same user name is already registered".equals(errorMessage)) {
			verificationErrors.append("Error message is not equals!! Expected \"Same user name is already registered\", but was "+errorMessage);
		}
		getXPathElement(UserPageElementPath.xpathUserIDInput).sendKeys(Keys.ESCAPE);
		Thread.sleep(3000);
		
		CommonFunctions.getInstance().deleteFirstEndUser(getDriver());
	}
	
	@Test
	public void testUserCreate_같은이메일중복등록() throws Exception {
		CommonFunctions.getInstance().createEndUser(getDriver(), "firstid_selenium", this.testUserPW, "samemail@test.com", null);
		Thread.sleep(5000);
		CommonFunctions.getInstance().createEndUser(getDriver(), "secondid_selenium", this.testUserPW, "samemail@test.com", null);
		
		String errorMessage = getXPathElement(UserPageElementPath.xpathUserCreateErrorMessageArea).getText();
		if(errorMessage ==null) {
			verificationErrors.append("Error message is null!!! ");
		}
		if(!"Same email address is already registered".equals(errorMessage)) {
			verificationErrors.append("Error message is not equals!! Expected \"Same email address is already registered\", but was "+errorMessage);
		}
		getXPathElement(UserPageElementPath.xpathUserIDInput).sendKeys(Keys.ESCAPE);
		Thread.sleep(3000);
		CommonFunctions.getInstance().deleteFirstEndUser(getDriver());
	}
	
}
