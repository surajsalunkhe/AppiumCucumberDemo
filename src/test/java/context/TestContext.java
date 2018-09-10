package context;

import driver.DriverFactory;
import driver.DriverManager;
import driver.PageObjectManager;
import stepDefinitions.CommonSteps;

import java.io.IOException;

public class TestContext {

    private PageObjectManager pageObjectManager;
    private DriverManager driverManager;
    public ScenarioContext scenarioContext;


    public TestContext() throws IOException{
        driverManager= new DriverManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public DriverManager getDriverManager(){return driverManager;}

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }


}
