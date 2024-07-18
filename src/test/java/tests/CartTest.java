package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.ConfigReader;

public class CartTest extends BaseTest {
    private ProductsPage productsPage;
    private String productName;

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(
                ConfigReader.getProperty("validUsername"),
                ConfigReader.getProperty("validPassword")
        );
        productsPage = new ProductsPage(getDriver());
        productName = ConfigReader.getTestData("productName");
    }

    @Test(description = "Verify product can be added to cart")
    public void addProductToCart() {
        Assert.assertTrue(
                productsPage.isProductDisplayed(productName),
                "Selected product should be displayed before adding to cart."
        );

        productsPage.addProductToCart(productName);
        CartPage cartPage = productsPage.openCart();

        Assert.assertTrue(
                cartPage.isProductInCart(productName),
                "Added product should be displayed in the cart."
        );
    }

    @Test(description = "Verify product can be removed from cart")
    public void removeProductFromCart() {
        productsPage.addProductToCart(productName);
        CartPage cartPage = productsPage.openCart();

        Assert.assertTrue(
                cartPage.isProductInCart(productName),
                "Product should be present in cart before removal."
        );

        cartPage.removeProduct(productName);

        Assert.assertTrue(
                cartPage.isCartEmpty(),
                "Cart should be empty after removing the product."
        );
    }
}
