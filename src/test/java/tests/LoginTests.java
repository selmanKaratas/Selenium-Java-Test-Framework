package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    // Test 1: Valid login test
    @Test(priority = 1)
    public void testSuccessfulLogin() {
        System.out.println("Test 1: Starting successful login test");

        loginPage.goToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        // Verify navigation to products page
        boolean isOnProducts = productsPage.isOnProductsPage();
        Assert.assertTrue(isOnProducts, "Login failed - not on products page");

        String title = productsPage.getPageTitle();
        Assert.assertEquals(title, "Products");

        System.out.println("Login successful!");
    }

    // Test 2: Invalid password test
    @Test(priority = 2)
    public void testWrongPassword() {
        System.out.println("Test 2: Starting incorrect password test");

        loginPage.goToLoginPage();
        loginPage.login("standard_user", "wrong_password");

        // Error message should be visible
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");

        // Should remain on the login page
        Assert.assertTrue(loginPage.isOnLoginPage());

        System.out.println("Incorrect password test passed");
    }

    // Test 3: Empty username test
    @Test(priority = 3)
    public void testEmptyUsername() {
        System.out.println("Test 3: Starting empty username test");

        loginPage.goToLoginPage();
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        // Verify error display
        Assert.assertTrue(loginPage.isErrorDisplayed());

        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Username is required"));

        System.out.println("Empty username test passed");
    }

    // Test 4: Verify products display
    @Test(priority = 4)
    public void testProductsShown() {
        System.out.println("Test 4: Verifying products are displayed");

        loginWithValidUser();

        Assert.assertTrue(productsPage.isOnProductsPage());

        int count = productsPage.getProductCount();
        System.out.println("Product count: " + count);

        Assert.assertTrue(count > 0, "No products found on page");

        System.out.println("Product display test passed");
    }

    // Test 5: Add to cart and logout test
    @Test(priority = 5)
    public void testAddCartAndLogout() {
        System.out.println("Test 5: Add to cart and logout flow");

        loginWithValidUser();

        // Add the first product
        productsPage.addFirstProductToCart();

        // Verify cart badge shows 1
        String cartCount = productsPage.getCartCount();
        Assert.assertEquals(cartCount, "1");
        System.out.println("Product added to cart");

        // Perform logout
        productsPage.logout();

        // Verify redirection to login page
        Assert.assertTrue(loginPage.isOnLoginPage());

        System.out.println("Add to cart and logout test passed");
    }
}