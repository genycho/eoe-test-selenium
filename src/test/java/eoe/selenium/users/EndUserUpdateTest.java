package eoe.selenium.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.NgWebDriver;

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
public class EndUserUpdateTest extends EoESeleniumTestCase{
	String testId = GUITestData.TEST_ID;
	String testPw = GUITestData.TEST_PW;
	
	String testUserID = "selenium_update_userid";
	String testUserPW = "selenium_update_userpw";
	String testUserEmail = "guitest@eoe.com";
	
	public EndUserUpdateTest() {
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
	public void testUserUpdate_기본() throws Exception {
		CommonFunctions.getInstance().createEndUser(getDriver(), testUserID, testUserPW, testUserEmail, null);
		/*	Moving to the detail page*/
		Thread.sleep(6000);
		GUITestUtils.scrollUpToTop(getDriver());
		Thread.sleep(1000);
		getXPathElement(UserPageElementPath.xpathFirstDataEmail).click();
		Thread.sleep(6000);
		getNgDriver().waitForAngularRequestsToFinish();
		
		/*	Checking each input fields 'readonly'(for input fields), 'disabled' (for button, controls)  property, readonly fields	*/
		//cannot modify
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
//		String enable077 = getXPathElement(UserPageElementPath.xpathUserDetailApplication).getAttribute("disabled");
//		assertNull(enable077);
		
		String enable08 = getXPathElement(UserPageElementPath.xpathUserDetailOpenBirthDatePopup).getAttribute("disabled");
		assertNull(enable08);
		String enable09 = getXPathElement(UserPageElementPath.xpathUserDetailCountry).getAttribute("disabled");
		assertNull(enable09);
//		String enable10 = getXPathElement(UserPageElementPath.xpathUserDetailGender).getAttribute("disabled");
//		String enable11 = getXPathElement(UserPageElementPath.xpathUserDetailOpenBirthDatePopup).getAttribute("disabled");
//		String enable12 = getXPathElement(UserPageElementPath.xpathUserDetailStatus).getAttribute("disabled");
//		String enable13 = getXPathElement(UserPageElementPath.xpathUserDetailVerifiedEmail).getAttribute("disabled");
//		String enable14 = getXPathElement(UserPageElementPath.xpathUserDetailVerifiedMobile).getAttribute("disabled");
		
		String beforeValue01 = getXPathElement(UserPageElementPath.xpathUserDetailAddress).getAttribute("value"); 
		getXPathElement(UserPageElementPath.xpathUserDetailAddress).sendKeys(Keys.TAB);
		getXPathElement(UserPageElementPath.xpathUserDetailAddress).clear();
		getXPathElement(UserPageElementPath.xpathUserDetailAddress).sendKeys("수정_mod_"+beforeValue01);
		
		String beforeValue02 = getXPathElement(UserPageElementPath.xpathUserDetailFirstName).getAttribute("value");
		getXPathElement(UserPageElementPath.xpathUserDetailFirstName).clear();
		getXPathElement(UserPageElementPath.xpathUserDetailFirstName).sendKeys("mod_"+beforeValue02);
		
		String beforeValue03 = getXPathElement(UserPageElementPath.xpathUserDetailMiddleName).getAttribute("value");
		getXPathElement(UserPageElementPath.xpathUserDetailMiddleName).clear();
		getXPathElement(UserPageElementPath.xpathUserDetailMiddleName).sendKeys("mod_"+beforeValue03);
		
		String beforeValue04 = getXPathElement(UserPageElementPath.xpathUserDetailLastName).getAttribute("value");
		getXPathElement(UserPageElementPath.xpathUserDetailLastName).clear();
		getXPathElement(UserPageElementPath.xpathUserDetailLastName).sendKeys("mod_"+beforeValue04);
		
		String beforeValue05 = getXPathElement(UserPageElementPath.xpathUserDetailEmail).getAttribute("value");
		getXPathElement(UserPageElementPath.xpathUserDetailEmail).clear();
		getXPathElement(UserPageElementPath.xpathUserDetailEmail).sendKeys("mod_"+beforeValue05);
		
		String beforeValue06 = getXPathElement(UserPageElementPath.xpathUserDetailMobile).getAttribute("value");
		getXPathElement(UserPageElementPath.xpathUserDetailMobile).clear();
		getXPathElement(UserPageElementPath.xpathUserDetailMobile).sendKeys("000"+beforeValue06);
		
		getXPathElement(UserPageElementPath.xpathUserDetailStatus).click();//aria-selected = true
		String activeSelected = getXPathElement(UserPageElementPath.xpathUserDetailStatusActivated).getAttribute("aria-selected"); 
		if(activeSelected ==null || !"true".equals(activeSelected)){
			getXPathElement(UserPageElementPath.xpathUserDetailStatusActivated).click();
		}
		
		getXPathElement(UserPageElementPath.xpathUserDetailGender).click();//aria-selected = true
		Thread.sleep(2000);
		String maleSelected = getXPathElement(UserPageElementPath.xpathUserDetailGenderMale).getAttribute("aria-selected"); 
		if(maleSelected ==null || !"true".equals(maleSelected)){
			getXPathElement(UserPageElementPath.xpathUserDetailGenderMale).click();
		}else {
			getXPathElement(UserPageElementPath.xpathUserDetailGenderFemale).click();
		}
		
		getXPathElement(UserPageElementPath.xpathUserDetailSaveBtn).click();
		Thread.sleep(6000);
		getNgDriver().waitForAngularRequestsToFinish();
		getXPathElement(UserPageElementPath.xpathGoToUserList).click();
		Thread.sleep(3000);
		getNgDriver().waitForAngularRequestsToFinish();
		CommonFunctions.getInstance().deleteFirstEndUser(getDriver());
	}
}
