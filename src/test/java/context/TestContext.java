package driver;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

public class TestContext {

    private PageObjectManager pageObjectManager;
    private DriverFactory driverFactory=new DriverFactory();

    public TestContext() throws IOException{
        pageObjectManager = new PageObjectManager(driverFactory.getDriver());
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}
