package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtil {
    private static final String SCREENSHOT_DIRECTORY = "screenshots";

    private ScreenshotUtil() {
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            return "";
        }

        try {
            Files.createDirectories(Path.of(SCREENSHOT_DIRECTORY));
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String screenshotName = testName + "_" + timestamp + ".png";
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Path.of(SCREENSHOT_DIRECTORY, screenshotName);
            Files.copy(sourceFile.toPath(), destination);
            return destination.toAbsolutePath().toString();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to capture screenshot for test: " + testName, exception);
        }
    }
}
