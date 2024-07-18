package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with a valid SauceDemo user")
    public void successfulLoginWithValidUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(
                ConfigReader.getProperty("validUsername"),
                ConfigReader.getProperty("validPassword")
        );

        ProductsPage productsPage = new ProductsPage(getDriver());
        Assert.assertEquals(
                productsPage.getPageTitle(),
                ConfigReader.getTestData("expectedProductsPageTitle"),
                "Products page title should be displayed after successful login."
        );
    }

    @Test(description = "Verify failed login with invalid credentials")
    public void failedLoginWithInvalidUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(
                ConfigReader.getProperty("invalidUsername"),
                ConfigReader.getProperty("invalidPassword")
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                ConfigReader.getTestData("expectedInvalidLoginError"),
                "Invalid login error message should match expected text."
        );
    }

    @Test(description = "Verify user can logout successfully")
    public void logoutSuccessfully() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(
                ConfigReader.getProperty("validUsername"),
                ConfigReader.getProperty("validPassword")
        );

        ProductsPage productsPage = new ProductsPage(getDriver());
        LoginPage loggedOutPage = productsPage.logout();

        Assert.assertTrue(
                loggedOutPage.isLoginButtonDisplayed(),
                "Login button should be visible after logout."
        );
    }
}
