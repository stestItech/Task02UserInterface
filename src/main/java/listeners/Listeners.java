package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReporter;
import utils.Utilities;

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, "Execution of " + result.getName() + " started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, result.getName() + " was successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver)result
                    .getTestClass()
                    .getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (IllegalAccessException |NoSuchFieldException e) {
            e.printStackTrace();
        }

        String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, result.getName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, result.getName() + " was skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }
}
