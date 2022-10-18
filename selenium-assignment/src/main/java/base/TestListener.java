package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class TestListener implements ITestListener{
    @Attachment(value = "Screenshot of {0}", type = "image/png")
	public byte[] addScreenshot(String name, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
    @Attachment(value = "{0}", type = "text/plain")
	public static String addTextValue(String message) {
		return message;
	}
    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        //addScreenshot(result.getName(), null);
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        
    }
    
}
