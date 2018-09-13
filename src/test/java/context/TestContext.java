package context;

import managers.WebDriverManager;
import managers.PageObjectManager;

import java.io.IOException;

public class TestContext {

    private PageObjectManager pageObjectManager;
    private WebDriverManager driverManager;
    public ScenarioContext scenarioContext;


    public TestContext() throws IOException{
        driverManager= new WebDriverManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public WebDriverManager getDriverManager(){return driverManager;}

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }


}
