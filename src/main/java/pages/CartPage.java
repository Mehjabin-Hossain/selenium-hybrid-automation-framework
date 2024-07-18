package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItems = By.cssSelector("[data-test='inventory-item']");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(
                Long.parseLong(ConfigReader.getProperty("explicitWait", "10"))
        ));
    }

    public boolean isProductInCart(String productName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductName(productName))).isDisplayed();
    }

    public void removeProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(removeButton(productName))).click();
    }

    public boolean isCartEmpty() {
        return wait.until(driver -> getCartItems().isEmpty());
    }

    public CheckoutPage clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return new CheckoutPage(driver);
    }

    private List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    private By cartProductName(String productName) {
        return By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']");
    }

    private By removeButton(String productName) {
        return By.xpath("//div[@data-test='inventory-item' and .//div[@data-test='inventory-item-name' and normalize-space()='"
                + productName + "']]//button[contains(@id,'remove')]");
    }
}
