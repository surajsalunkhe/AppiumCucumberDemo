package driver;

import io.appium.java_client.android.AndroidDriver;
import pages.HomePage;
import stepDefinitions.CommonSteps;

public class PageObjectManager {

    private AndroidDriver driver;
    private HomePage homePage;
    private CommonSteps commonSteps;

    public PageObjectManager(AndroidDriver driver)
    {
        this.driver=driver;
    }
    public HomePage getHomePage(){
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }
    public CommonSteps getCommonFunction(){
        return (commonSteps == null) ? commonSteps = new CommonSteps(driver) : commonSteps;
    }
}
