package stepDefinitions;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import context.TestContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;

public class Hooks {
    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void BeforeSteps() {
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
        Reporter.assignAuthor("ToolsQA - Suraj Salunkhe");
    }

    @After(order = 1)
        public void afterScenario(Scenario scenario) {
            if (scenario.isFailed()) {
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                try {
                    //This takes a screenshot from the managers at save it to the specified location
                    File sourcePath = ((TakesScreenshot) testContext.getDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);

                    //Building up the destination path for the screenshot to save
                    //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                    File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");

                    //Copy taken screenshot from source location to destination location
                    Files.copy(sourcePath, destinationPath);

                    //This attach the specified screenshot to the test
                    Reporter.addScreenCaptureFromPath(destinationPath.toString());
                } catch (IOException e) {
                }
            }
    }
    @After(order = 0)
    public void AfterSteps(){
        testContext.getDriverManager().quitDriver();
    }

}
