package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.ConfigReader;

public class CheckoutTest extends BaseTest {
    private CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void addProductAndOpenCheckout() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(
                ConfigReader.getProperty("validUsername"),
                ConfigReader.getProperty("validPassword")
        );

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.addProductToCart(ConfigReader.getTestData("productName"));

        CartPage cartPage = productsPage.openCart();
        checkoutPage = cartPage.clickCheckout();
    }

    @Test(description = "Verify complete checkout flow")
    public void completeCheckoutFlow() {
        checkoutPage.enterCheckoutInformation(
                ConfigReader.getTestData("firstName"),
                ConfigReader.getTestData("lastName"),
                ConfigReader.getTestData("postalCode")
        );
        checkoutPage.finishCheckout();

        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                ConfigReader.getTestData("expectedOrderConfirmation"),
                "Order confirmation message should be displayed after checkout."
        );
    }

    @Test(description = "Verify order confirmation message")
    public void verifyOrderConfirmationMessage() {
        checkoutPage.enterCheckoutInformation(
                ConfigReader.getTestData("firstName"),
                ConfigReader.getTestData("lastName"),
                ConfigReader.getTestData("postalCode")
        );
        checkoutPage.finishCheckout();

        Assert.assertTrue(
                checkoutPage.getConfirmationMessage().contains("Thank you"),
                "Order confirmation message should confirm successful order placement."
        );
    }
}
