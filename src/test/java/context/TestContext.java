package context;

import driver.DriverFactory;
import driver.PageObjectManager;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

public class TestContext {

    private PageObjectManager pageObjectManager;
    private DriverFactory driverFactory;
    public ScenarioContext scenarioContext;


    public TestContext() throws IOException{
        pageObjectManager = new PageObjectManager(driverFactory.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }


}
