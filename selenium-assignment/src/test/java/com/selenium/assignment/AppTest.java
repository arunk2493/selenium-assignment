package com.selenium.assignment;

import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import io.qameta.allure.Description;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    @Description("Sample assertion")
    public void shouldAnswerWithTrue()
    {
        int a = 5;
        int b = 10;
        Assert.assertTrue( a<b );
    }
}
