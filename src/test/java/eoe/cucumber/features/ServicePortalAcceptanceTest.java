package eoe.cucumber.features;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
//		glue= {}
		format = "junit:target/junit.xml",
	    features = "classpath:eoe/cucumber/features"
)
public class ServicePortalAcceptanceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

}
