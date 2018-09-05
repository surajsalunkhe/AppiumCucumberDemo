package stepDefinitions;

import context.TestContext;
import pages.HomePage;

public class HomePageSteps {
	TestContext testContext;
	HomePage homePage;


	public HomePageSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
	}


}
