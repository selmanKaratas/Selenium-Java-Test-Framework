package tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class EndToEndTests extends BaseTest {

    @Test(priority = 13, description = "Verify end-to-end shopping flow")
    public void testFullShoppingJourney() {
        loginWithValidUser();
        productsPage.addMultipleToCart(2);
        productsPage.clickCart();
        cartPage.clickCheckout();

        checkoutPage.fillInfo();
        checkoutPage.clickContinue();

        // Local wait to ensure the page transition completes before Assertion
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"),
                "Error: Page did not transition to step two!");
    }

    @Test(priority = 14, description = "Verify system security: No access to internal pages after logout")
    public void testLogoutSecurity() {
        loginWithValidUser();
        productsPage.logout();
        driver.navigate().back();
        Assert.assertTrue(loginPage.isOnLoginPage(), "User should be redirected to login page after logout!");
    }

    @Test(priority = 15, description = "Verify social media links are present in the footer")
    public void testSocialLinksPresence() {
        loginWithValidUser();
        Assert.assertTrue(driver.getPageSource().contains("Twitter"), "Twitter link missing!");
        Assert.assertTrue(driver.getPageSource().contains("Facebook"), "Facebook link missing!");
    }

    @Test(priority = 16, description = "DEMO: Automatic Screenshot on Failure")
    public void testIntentionalFailure() {
        loginWithValidUser();
        test.log(Status.INFO, "Checking for a wrong condition to trigger failure");
        // This is meant to fail for demonstration purposes
        Assert.assertEquals(productsPage.isOnProductsPage(), false, "Demonstrating failure screenshot!");
    }
}