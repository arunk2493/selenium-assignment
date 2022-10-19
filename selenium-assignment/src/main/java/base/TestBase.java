package base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class TestBase {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected Properties propertyConfig;
    public static final Logger LOGGER = Logger.getLogger(TestBase.class.getName());
    public static final String remote_url_chrome = "http://localhost:4447/wd/hub";

    @BeforeTest
    public void readConfigs() throws IOException{
        TestConfiguration config = new TestConfiguration();
        propertyConfig = config.loadConfig();
    }
    @BeforeClass
    public void initiateDriver() throws MalformedURLException{
        LOGGER.info("------Driver initiation-----");
        String browserName = propertyConfig.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "../selenium-assignment/drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.get("https://www.amazon.in/");
        }else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "../selenium-assignment/drivers/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options); 
            driver.get("https://www.amazon.in/");
        }else if(browserName.equalsIgnoreCase("remote")){
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            driver = new RemoteWebDriver(new URL(remote_url_chrome),options);
            driver.get("https://www.amazon.in/");
        }else{
            LOGGER.warning("Unsupported browser:"+browserName);
        }

    }
    
    public static WebDriver getDriver(){
        return driver;
    }
    public static void waitForElement(WebElement elementToWait){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(elementToWait));
    }
    public static void scrollElementIntoView(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public void switchToWindow(){
        String amazonWindowParent=driver.getWindowHandle();
        Set<String>productDetailsWindows=driver.getWindowHandles();
        Iterator<String> I1= productDetailsWindows.iterator();
        while(I1.hasNext())
        {
        String child_window=I1.next();
        if(!amazonWindowParent.equals(child_window)){
            driver.switchTo().window(child_window);
         }
        }
    }

    public static void selectDropDownValue(String value,WebElement element,WebElement clickaWebElement){
        clickaWebElement.click();
        Select selectDropDown = new Select(element);
        selectDropDown.selectByVisibleText(value);
    }
    
    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    
}
