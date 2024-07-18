package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By inventoryItems = By.cssSelector("[data-test='inventory-item']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(
                Long.parseLong(ConfigReader.getProperty("explicitWait", "10"))
        ));
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean isProductListDisplayed() {
        return !getProducts().isEmpty();
    }

    public int getProductCount() {
        return getProducts().size();
    }

    public boolean isProductDisplayed(String productName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator(productName))).isDisplayed();
    }

    public void addProductToCart(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton(productName))).click();
    }

    public void removeProductFromCart(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(removeButton(productName))).click();
    }

    public CartPage openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        return new CartPage(driver);
    }

    public LoginPage logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        return new LoginPage(driver);
    }

    private List<WebElement> getProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItems));
        return driver.findElements(inventoryItems);
    }

    private By productNameLocator(String productName) {
        return By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']");
    }

    private By addToCartButton(String productName) {
        return By.xpath("//div[@data-test='inventory-item' and .//div[@data-test='inventory-item-name' and normalize-space()='"
                + productName + "']]//button[contains(@id,'add-to-cart')]");
    }

    private By removeButton(String productName) {
        return By.xpath("//div[@data-test='inventory-item' and .//div[@data-test='inventory-item-name' and normalize-space()='"
                + productName + "']]//button[contains(@id,'remove')]");
    }
}
