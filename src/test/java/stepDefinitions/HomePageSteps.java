package stepDefinitions;

import driver.TestContext;
import io.appium.java_client.android.AndroidDriver;
import pages.HomePage;

public class HomePageSteps {
	TestContext testContext;
	HomePage homePage;
	

	public HomePageSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
	}


}
