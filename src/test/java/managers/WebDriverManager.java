package managers;

import enums.WebDriverType;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static WebDriverType driverType;
    private WebDriver driver;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.managers";
    private static final String MOZILLA_DRIVER_PROPERTY = "webdriver.gecko.managers";
    private static final String IE_DRIVER_PROPERTY = "webdriver.ie.managers";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public void getDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX:
                System.setProperty(MOZILLA_DRIVER_PROPERTY,FileReaderManager.getInstance().getConfigReader().getMozillaDriverPath());
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER:
                System.setProperty(IE_DRIVER_PROPERTY,FileReaderManager.getInstance().getConfigReader().getIEDriverPath());
                driver = new InternetExplorerDriver();
                break;
        }
        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    public void closeDriver() {
        driver.close();
    }
    public void quitDriver(){
        driver.quit();
    }
}
