package base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class TestBase {
    protected static WebDriver driver;
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
            driver = new RemoteWebDriver(new URL(remote_url_chrome),options);
            driver.get("https://www.amazon.in/");
        }else{
            LOGGER.warning("Unsupported browser:"+browserName);
        }

    }
    
    public static WebDriver getDriver(){
        return driver;
    }
    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    
}
