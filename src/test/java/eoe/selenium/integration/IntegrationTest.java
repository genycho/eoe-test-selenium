package eoe.selenium.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.NgWebDriver;

import eoe.selenium.EoESeleniumTestCase;
import eoe.selenium.GUITestException;
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
public class IntegrationTest extends EoESeleniumTestCase{
	
	String testId = GUITestData.TEST_ID;
	String testPw = GUITestData.TEST_PW;
	
	String testUserID = "sel_intg_userid";
	String testUserPW = "sel_intg_userpw";
	String testUserEmail = "intgtest@eoe.com";
	
	public IntegrationTest() {
		super();
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
	public void testWorkflow_기본() throws Exception {
		//1. 로그인
		CommonFunctions.getInstance().login(getDriver(), this.baseUrl, testId, testPw);
		Thread.sleep(3000);
		getNgDriver().waitForAngularRequestsToFinish();
		//1.1	로그인 후 logged in user id 확인
		String loginUserId = getXPathElement(UserPageElementPath.xpathLoggedInUserId).getText();
		assertNotNull(loginUserId);
		assertEquals(testId, loginUserId);

		//1.2	로그인 후 디폴트 페이지의 타이틀 확인
//		String pageTitle = getXPathElement(UserPageElementPath.xpathUserTitle).getText();
//		assertNotNull(pageTitle);
//		assertEquals("Users", pageTitle);
		
		//2. Application 목록 조회, 추가/수정/삭제
		//TODO	Application 기능 불안정 등으로 제외

		//3. User 목록 조회
		CommonFunctions.getInstance().moveToUserListPage(getDriver());
		Thread.sleep(3000);
		//3.1 조회된 user count가 최소한 1건 이상이라는 전제 검증
		String userCount = GUITestUtils.getXPathElement(getDriver(), UserPageElementPath.xpathUserListCount).getText();
		assertNotNull(userCount);
		int userListCount = Integer.valueOf(userCount.split(" ")[0].trim());
		assertTrue(userListCount > 0);
		
		//4. 신규 User 생성
		CommonFunctions.getInstance().createEndUser(getDriver(), testUserID, testUserPW, testUserEmail, null);
		Thread.sleep(6000);
		getNgDriver().waitForAngularRequestsToFinish();
		//4.1 생성한 유저의 이메일로 조회
		//TODO	현재 조건조회 미구현.
//		CommonFunctions.getInstance().searchAUserWithEmailInUserList(getDriver(), testUserEmail); 
//		Thread.sleep(3000);
//		getNgDriver().waitForAngularRequestsToFinish();
		
		userCount = GUITestUtils.getXPathElement(getDriver(), UserPageElementPath.xpathUserListCount).getText();
		assertNotNull(userCount);
		int beforeUserCount = Integer.valueOf(userCount.split(" ")[0].trim());
		assertTrue(beforeUserCount > 0);
		
		//5. User 삭제
		CommonFunctions.getInstance().deleteFirstEndUser(getDriver());
		Thread.sleep(3000);
		getNgDriver().waitForAngularRequestsToFinish();
		//5.1	최초 생성했던 유저 이메일로 조회 
		//TODO	현재 조건조회 미구현.
//		CommonFunctions.getInstance().searchAUserWithEmailInUserList(getDriver(), testUserEmail);
//		Thread.sleep(3000);
//		getNgDriver().waitForAngularRequestsToFinish();
		//5.2	신규 생성 시의 이메일 조회 건수보다 -1된 것 확인  
		userCount = GUITestUtils.getXPathElement(getDriver(), UserPageElementPath.xpathUserListCount).getText();
		assertNotNull(userCount);
		int afterUserCount = Integer.valueOf(userCount.split(" ")[0].trim());
		assertEquals(beforeUserCount -1, afterUserCount);
		
		//7. 로그아웃
		CommonFunctions.getInstance().logout(getDriver());
		Thread.sleep(3000);
		getNgDriver().waitForAngularRequestsToFinish();
	}
}