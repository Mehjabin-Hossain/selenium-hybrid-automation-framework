package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;

import java.time.Duration;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browser) {
        String selectedBrowser = browser != null ? browser : ConfigReader.getProperty("browser", "chrome");

        if (!"chrome".equalsIgnoreCase(selectedBrowser)) {
            throw new IllegalArgumentException("Only Chrome browser execution is currently supported.");
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");

        if (Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"))
                || Boolean.parseBoolean(System.getenv("CI"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait", "5")))
        );
        webDriver.manage().window().maximize();
        webDriver.get(ConfigReader.getProperty("baseUrl"));
        driver.set(webDriver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
