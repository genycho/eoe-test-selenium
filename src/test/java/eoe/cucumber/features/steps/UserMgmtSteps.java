package eoe.cucumber.features.steps;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import eoe.selenium.AbstractSeleniumTestCase;
import eoe.selenium.uielement.LonginPageElementPath;

public class UserMgmtSteps extends AbstractSeleniumTestCase  {

	public UserMgmtSteps() {
		super.readTestEnvValues();
		setBasicEnv();
	}
	
	@Before
	private void setUp() {
	}
	
	@After
	private void tearDown() {
		getXPathElement(LonginPageElementPath.xpathLogout).click();
		getDriver().quit();
	}
	
	@Given("^eoe 서비스 포탈에 접속한다$")
	public void eoe_서비스_포탈에_접속한다() throws Throwable {
		getDriver().manage().window().maximize() ;
		getDriver().get(baseUrl);
		getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		getNgDriver().waitForAngularRequestsToFinish();
	}
	
	@When("^로그인아이디 \"(.*?)\", 비밀번호 \"(.*?)\"로 로그인한다$")
	public void 로그인아이디_비밀번호_로_로그인한다(String loginId, String loginPw) throws Throwable {
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
		getXPathElement(LonginPageElementPath.xpathId).clear();
		getXPathElement(LonginPageElementPath.xpathId).sendKeys(loginId);
		getXPathElement(LonginPageElementPath.xpathPw).clear();
		getXPathElement(LonginPageElementPath.xpathPw).sendKeys(loginPw);
		getNgButtonTxt("Login").click();
	}

	@Then("^사용자목록조회페이지가표시된다$")
	public void 사용자목록조회페이지가표시된다() throws Throwable {
		waitForElementPresent(LonginPageElementPath.xpathEoETitle);
	}

	@Then("^로그인페이지가 계속 표시된다$")
	public void 로그인페이지가_계속_표시된다() throws Throwable {
		waitForElementPresent(LonginPageElementPath.xpathLoginArea);
	}

	@Then("^로그인 성공 메시지가 표시된다$")
	public void 로그인_성공_메시지가_표시된다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^에러메시지\"(.*?)\"이 표시된다$")
	public void 에러메시지_이_표시된다(String arg1) throws Throwable {
		waitForElementPresent(LonginPageElementPath.xpathErrorMessage);
		Thread.sleep(1000);
		String errorMessage = getXPathElement(LonginPageElementPath.xpathErrorMessage).getText();
		assertEquals("Bad credentials", errorMessage);
	}
}
