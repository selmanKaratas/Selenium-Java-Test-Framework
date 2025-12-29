package tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        test.log(Status.INFO, "Navigating to Login Page");
        loginPage.goToLoginPage();

        test.log(Status.INFO, "Performing login with standard_user");
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productsPage.isOnProductsPage(), "Login failed - not on products page");
        Assert.assertEquals(productsPage.getPageTitle(), "Products");

        test.log(Status.PASS, "Successfully redirected to Products page");
    }

    @Test(priority = 2)
    public void testWrongPassword() {
        test.log(Status.INFO, "Navigating to Login Page");
        loginPage.goToLoginPage();

        test.log(Status.INFO, "Entering wrong credentials");
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
        Assert.assertTrue(loginPage.isOnLoginPage(), "User should still be on Login Page");

        test.log(Status.PASS, "Incorrect password handled correctly");
    }

    @Test(priority = 3)
    public void testEmptyUsername() {
        test.log(Status.INFO, "Navigating to Login Page");
        loginPage.goToLoginPage();

        test.log(Status.INFO, "Entering only password and clicking login");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message missing for empty username");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));

        test.log(Status.PASS, "Empty username validation passed");
    }

    @Test(priority = 4)
    public void testProductsShown() {
        test.log(Status.INFO, "Performing valid login");
        loginWithValidUser();

        test.log(Status.INFO, "Verifying product visibility");
        Assert.assertTrue(productsPage.isOnProductsPage());
        int count = productsPage.getProductCount();

        test.log(Status.INFO, "Product count found: " + count);
        Assert.assertTrue(count > 0, "No products found on page");

        test.log(Status.PASS, "Products are displayed correctly");
    }

    @Test(priority = 5)
    public void testAddCartAndLogout() {
        test.log(Status.INFO, "Logging in and adding first product to cart");
        loginWithValidUser();
        productsPage.addFirstProductToCart();

        test.log(Status.INFO, "Checking cart badge");
        Assert.assertEquals(productsPage.getCartCount(), "1");

        test.log(Status.INFO, "Logging out from the application");
        productsPage.logout();

        Assert.assertTrue(loginPage.isOnLoginPage(), "Redirection to login page failed after logout");
        test.log(Status.PASS, "Cart flow and logout successful");
    }
}