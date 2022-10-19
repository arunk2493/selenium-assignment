package com.selenium.assignment;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.TestBase;
import base.TestListener;
import io.qameta.allure.Description;
import pages.AmazonPage;
@Listeners({TestListener.class})
public class AssessmentTest extends TestBase {
    AmazonPage amazonPage;
    @Test(priority = 1)
    @Description("Assessment for Amazon")
    public void AssessmentAmazon() {
        amazonPage = new AmazonPage(driver);
        amazonPage.clickHamBurgerMenuItem();
        amazonPage.clickCategoryItem();
        amazonPage.clickTelevisionMenu();
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
