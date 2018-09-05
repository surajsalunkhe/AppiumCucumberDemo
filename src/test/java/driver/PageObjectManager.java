package driver;

import io.appium.java_client.android.AndroidDriver;
import pages.HomePage;

public class PageObjectManager {
    private AndroidDriver driver;
    private HomePage homePage;

    public PageObjectManager(AndroidDriver driver){
        this.driver=driver;
    }
    public HomePage getHomePage(){

        return (homePage == null) ? homePage = new HomePage(driver) : homePage;

    }
}
