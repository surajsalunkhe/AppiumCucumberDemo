package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(AndroidDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
}
