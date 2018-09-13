package Runner;
import com.cucumber.listener.ExtentProperties;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import driver.FileReaderManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import java.io.File;

@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "/src/test/java/features",
        tags = {"@TestngScenario"},
        glue = {"/src/test/java/stepDefinitions"},
        plugin = { "pretty", "html:target/cucumber-html-report",
                "com.cucumber.listener.ExtentCucumberFormatter:"}
        )

public class TestRunner extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("ExecutionReport/myreport.html");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
        Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", 	"macOS Sierra" + "64 Bit");
        Reporter.setSystemInfo("Maven", "3.5.2");
        Reporter.setSystemInfo("Java Version", "1.8.0_101");
    }
}
