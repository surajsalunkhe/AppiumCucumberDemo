package driver;

import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;

public class DriverManager  {
    private  AndroidDriver<?> driver;
    private DriverFactory driverFactory;
    public AndroidDriver<?> getDriver()throws IOException {
        if(driver == null) driver = driverFactory.createLocalDriver();
        return driver;
    }
    public void closeDriver() {
        driver.close();
    }
    public void quitDriver(){
        driver.quit();
    }
}
