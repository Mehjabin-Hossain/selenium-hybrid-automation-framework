package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ExtentReportManager {
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentReportManager() {
    }

    public static synchronized ExtentReports getReportInstance() {
        if (extentReports == null) {
            try {
                Files.createDirectories(Path.of("reports"));
            } catch (Exception exception) {
                throw new RuntimeException("Unable to create reports directory.", exception);
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = Path.of("reports", "ExtentReport_" + timestamp + ".html").toString();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Selenium Hybrid Automation Report");
            sparkReporter.config().setReportName("SauceDemo Regression Test Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Project", "Selenium Hybrid Automation Framework");
            extentReports.setSystemInfo("Application", "SauceDemo");
            extentReports.setSystemInfo("Tester", "Your Name");
        }

        return extentReports;
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void removeTest() {
        extentTest.remove();
    }

    public static synchronized void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
