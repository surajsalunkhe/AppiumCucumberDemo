package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.Set;


public class CommonSteps extends DriverFactory {

    private AndroidDriver driver;
    Logger logger = Logger.getLogger(CommonSteps.class);
    public CommonSteps(AndroidDriver driver) {
        super(driver);
    }

    @Given("^I launch the app$")
    @When("^I launch the app in When$")
    public void i_launch_the_app() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver= createLocalDriver();
    }

    @Given("^I wait for app to load$")
    @When("^I wait for app to load in When$")
    @Then("^I wait for app to load in Then$")
    public void waitForAppToLoad() throws Throwable {
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.wait(10);
    }

    @Given("^I wait for element id \"([^\"]*)\" to load$")
    @When("^I wait for element id \"([^\"]*)\" to load in When$")
    @Then("^I wait for element id \"([^\"]*)\" to load in Then$")
    public void i_wait_for_element_to_load_id(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element=driver.findElement(By.id(arg1));
        logger.info("Waiting For element to load="+arg1);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Given("^I wait for element xpath \"([^\"]*)\" to load$")
    @When("^I wait for element xpath \"([^\"]*)\" to load in When$")
    @Then("^I wait for element xpath \"([^\"]*)\" to load in Then$")
    public void i_wait_for_element_to_load_xpath(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element=driver.findElement(By.xpath(arg1));
        logger.info("Waiting For element to load="+arg1);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    @Given("^I get current rotation of screen$")
    public void GetCurrentRotation()
    {
        driver.getOrientation();
        logger.info("Current rotation of screen is ="+driver.getOrientation());
    }

    @Given("^I set Portrait rotation of screen$")
    @When("^I set Portrait rotation of screen in when$")
    @Then("^I set Portrait rotation of screen in then$")
    public void RotationPortait()
    {
        logger.info("Current rotation set to PORTRAIT=");
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Given("^I set Landscape rotation of screen$")
    @When("^I set Landscape rotation of screen in when$")
    @Then("^I set Landscape rotation of screen in then$")
    public void RotationLandscape()
    {
        logger.info("Current rotation set to Landscape=");
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    /**
     * method to set the context to required view.
     *
     * @param context view to be set
     */
    @Given("^I switch to WebView$")
    @When("^I switch to WebView in when$")
    @Then("^I switch to WebView in then$")
    public void setContext(String context) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1

        logger.info("Current context" + driver.getContext());
    }

    @Given("^I scroll page up$")
    @When("^I scroll page up in when$")
    @Then("^I scroll page up in then$")
    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

}
