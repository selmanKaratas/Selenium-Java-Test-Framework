package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test(priority = 1, description = "Verify user can login with valid credentials")
    public void testValidLogin() {
        loginWithValidUser();
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Test(priority = 2, description = "Verify error message for invalid password")
    public void testInvalidPassword() {
        loginPage.goTo();
        loginPage.login("standard_user", "wrong_pass");
        Assert.assertTrue(loginPage.getError().contains("do not match"));
    }

    @Test(priority = 3, description = "Verify error message for locked out user")
    public void testLockedUser() {
        loginPage.goTo();
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getError().contains("locked out"));
    }

    @Test(priority = 4, description = "Verify error message for empty username field")
    public void testEmptyUsername() {
        loginPage.goTo();
        loginPage.login("", "secret_sauce");
        Assert.assertTrue(loginPage.getError().contains("Username is required"));
    }
}