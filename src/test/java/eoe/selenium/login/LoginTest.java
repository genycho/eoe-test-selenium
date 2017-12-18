package eoe.selenium.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;

import eoe.selenium.EoESeleniumTestCase;
import eoe.selenium.common.GUITestData;
import eoe.selenium.uielement.LonginPageElementPath;
import eoe.selenium.uielement.UserPageElementPath;

/**
 * EoE Login Test	</br>
 * 
 * 참고 :  https://github.com/paul-hammant/ngWebDriver/blob/master/src/test/java/com/paulhammant/ngwebdriver/AngularAndWebDriverTest.java
 *
 */
public class LoginTest extends EoESeleniumTestCase{
	String testId = GUITestData.TEST_ID;
	String testPw = GUITestData.TEST_PW;
	
	public LoginTest() {
		super();
		baseUrl=baseUrl+"/login";
		setNewlyOpenBrowser(true);
		setCloseBrowser(true);
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
	public void testLogin_기본() throws Exception {
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
		
		getXPathElement(LonginPageElementPath.xpathId).clear();
		getXPathElement(LonginPageElementPath.xpathId).sendKeys(this.testId);
		getXPathElement(LonginPageElementPath.xpathPw).clear();
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys(this.testPw);
		getNgButtonTxt("Login").click();
		waitForElementPresent(LonginPageElementPath.xpathEoETitle);
		
		getXPathElement(LonginPageElementPath.xpathLogout).click();
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
	}
	
	@Test
	public void testLogin_존재하지않는아이디() throws Exception {
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
		
		getXPathElement(LonginPageElementPath.xpathId).clear();
		getXPathElement(LonginPageElementPath.xpathId).sendKeys("NOT_EXIST_ID");
		getXPathElement(LonginPageElementPath.xpathPw).clear();
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys(this.testPw);
		getNgButtonTxt("Login").click();
		
		waitForElementPresent(LonginPageElementPath.xpathErrorMessage);
		
		String errorMessage = getXPathElement(LonginPageElementPath.xpathErrorMessage).getText();
		assertEquals("Bad credentials", errorMessage);
	}
	
	@Test
	public void testLogin_맞지않는비밀번호() throws Exception {
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
		getXPathElement(LonginPageElementPath.xpathId).clear();
		getXPathElement(LonginPageElementPath.xpathId).sendKeys(this.testId);
		getXPathElement(LonginPageElementPath.xpathPw).clear();
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys("invalidpw");
		getNgButtonTxt("Login").click();
		
		waitForElementPresent(LonginPageElementPath.xpathErrorMessage);
		
		waitForElementPresent(LonginPageElementPath.xpathErrorMessage);
		
		String errorMessage = getXPathElement(LonginPageElementPath.xpathErrorMessage).getText();
		assertEquals("Bad credentials", errorMessage);
	}
	
	@Test
	public void testLogin_DISABLED체크() throws Exception {
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
		
		/* ID, PW 입력 시 disabled 값 null	*/
		getXPathElement(LonginPageElementPath.xpathId).clear();
		getXPathElement(LonginPageElementPath.xpathId).sendKeys(this.testId);
		getXPathElement(LonginPageElementPath.xpathPw).clear();
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys(this.testPw);
		String disabled = getNgButtonTxt("Login").getAttribute("disabled");
		assertNull(disabled);
		
		/* ID 미입력 시 로그인 버튼 disabled 값 true	*/
		getXPathElement(LonginPageElementPath.xpathId).clear();
		getXPathElement(LonginPageElementPath.xpathId).sendKeys("a");
		getXPathElement(LonginPageElementPath.xpathId).sendKeys(Keys.BACK_SPACE);
		getXPathElement(LonginPageElementPath.xpathId).click();
		disabled = getNgButtonTxt("Login").getAttribute("disabled");
		assertNotNull(disabled);
		assertEquals("true",disabled);
		
		/* PW  미입력 시 로그인 버튼 disabled 값 true	*/
		getXPathElement(LonginPageElementPath.xpathId).sendKeys(this.testId);
		disabled = getNgButtonTxt("Login").getAttribute("disabled");
		assertNull(disabled);
		getXPathElement(LonginPageElementPath.xpathPw).clear();
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys("a");
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys(Keys.BACK_SPACE);
		disabled = getNgButtonTxt("Login").getAttribute("disabled");
		assertNotNull(disabled);
		assertEquals("true",disabled);
	}
}
