package driver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import config.ADB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DriverFactory {
	private  AndroidDriver<?> driver;
    private  Properties prop = new Properties();
    private  InputStream input = null;
    private  Logger logger = Logger.getLogger(DriverFactory.class);

    public DriverFactory(AndroidDriver driver) {
        this.driver=driver;
    }

    public AndroidDriver createLocalDriver() throws IOException {
        String PropertyFilePath=System. getProperty("user.dir")+"/src/test/java/config/device.properties";
        File datafile=new File(PropertyFilePath);
    	input = new FileInputStream(datafile);
        prop.load(input);
        if (prop.getProperty("platform").equalsIgnoreCase("android")) {
            logger.info("Device property found for Android ");
            ADB adbdriver=new ADB(prop.getProperty("DeviceID"));
            boolean result=adbdriver.isAppAlradyInstalled(prop.getProperty("Package"));
            if(result==true){
                logger.info("App is alredy installed clearing content");
                adbdriver.clearAppsData(prop.getProperty("Package"));
            }
            else{
                adbdriver.installApp(System.getProperty("user.dir")+ prop.getProperty("AppPath"),prop.getProperty("Package"));
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("App Installed Succesfully");
            }
            AndroidSetup();

        } else {
            if (prop.getProperty("platform").equalsIgnoreCase("ios")) {
                logger.info("Device property found for iOS ");
                //iosSetup();
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public AndroidDriver AndroidSetup() throws MalformedURLException {
        logger.info("Setting Android Driver");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", prop.getProperty("DeviceID"));
        caps.setCapability("app", System.getProperty("user.dir") + prop.getProperty("AppPath"));
        caps.setCapability("package", prop.getProperty("Package"));
        caps.setCapability("appActivity", prop.getProperty("Activity"));
        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, prop.getProperty("WaitActivity"));
        caps.setCapability("newCommandTimeout", 10000);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        logger.info("Android Driver set succesfully");
        return driver;
    }
    /*public static AppiumDriver iosSetup() throws MalformedURLException {
    logger.info("Setting iOS Driver");
    DesiredCapabilities caps = new DesiredCapabilities();
    File classpathRoot = new File(System.getProperty("user.dir"));
    File appDir = new File(classpathRoot, "/build/");
    File app = new File(appDir, "");
    caps.setCapability("platformVersion", "9.2");
    caps.setCapability("deviceName", "iPhone 6");
    caps.setCapability("app", app.getAbsolutePath());
    driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    logger.info("iOS Driver set succesfully");
    return driver;
	}*/
}
