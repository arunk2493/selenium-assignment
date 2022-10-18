package com.selenium.assignment;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.TestBase;
import base.TestListener;
import io.qameta.allure.Description;
import pages.AmazonPage;
@Listeners({TestListener.class})
public class AssessmentTest extends TestBase {
    /* WebDriver driver;
    WebDriverWait wait;
    AmazonPage amazonPage;
    public static final String remote_url_chrome = "http://localhost:4446/wd/hub";
    private static final Logger LOGGER = Logger.getLogger(AssessmentTest.class.getName()); */
    AmazonPage amazonPage;
    /* @BeforeTest
    public void setUp() throws MalformedURLException{
        System.setProperty("webdriver.chrome.driver", "../selenium-assignment/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        /* ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new RemoteWebDriver(new URL(remote_url_chrome), options); */
    //} 
    @Test(priority = 1)
    @Description("Assessment for Amazon")
    public void AssessmentAmazon(){
        amazonPage = new AmazonPage(driver);
        amazonPage.clickHamBurgerMenuItem();
        amazonPage.clickCategoryItem();
        amazonPage.clickTelevisionMenu();
        amazonPage.scrollElementIntoView(amazonPage.txtBrand);
        amazonPage.filterSpecificBrand("Samsung");
        amazonPage.sortBy();
        amazonPage.clickSecondItem();
        amazonPage.switchToWindow();
        String itemHeader = amazonPage.getAboutThisHeader();
        Assert.assertEquals(itemHeader,"About this item");
        String aboutTheItemValue =amazonPage.getAboutThisItemValue();
        LOGGER.info(aboutTheItemValue);
        TestListener.addTextValue(aboutTheItemValue);
    }
}
