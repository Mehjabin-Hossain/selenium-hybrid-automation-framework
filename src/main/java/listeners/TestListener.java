package listeners;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportManager;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getReportInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.setTest(
                ExtentReportManager.getReportInstance().createTest(result.getMethod().getMethodName())
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().pass("Test passed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(BaseTest.getDriver(), result.getMethod().getMethodName());

        if (!screenshotPath.isBlank()) {
            ExtentReportManager.getTest().fail(
                    result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
            );
        } else {
            ExtentReportManager.getTest().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
        ExtentReportManager.removeTest();
    }
}
