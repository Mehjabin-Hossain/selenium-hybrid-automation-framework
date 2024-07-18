package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.ConfigReader;

public class ProductTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(
                ConfigReader.getProperty("validUsername"),
                ConfigReader.getProperty("validPassword")
        );
        productsPage = new ProductsPage(getDriver());
    }

    @Test(description = "Verify products page title")
    public void verifyProductsPageTitle() {
        Assert.assertEquals(
                productsPage.getPageTitle(),
                ConfigReader.getTestData("expectedProductsPageTitle"),
                "Products page title should match expected text."
        );
    }

    @Test(description = "Verify product list is displayed")
    public void verifyProductListIsDisplayed() {
        Assert.assertTrue(
                productsPage.isProductListDisplayed(),
                "Product list should be visible on the products page."
        );
        Assert.assertTrue(
                productsPage.getProductCount() > 0,
                "At least one product should be available."
        );
    }
}
