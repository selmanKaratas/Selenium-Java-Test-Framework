package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingTests extends BaseTest {
    @Test(priority = 5, description = "Verify adding a single product to cart")
    public void testAddToCart() {
        loginWithValidUser();
        productsPage.addFirstToCart();
        Assert.assertEquals(productsPage.getCartCount(), "1");
    }

    @Test(priority = 6, description = "Verify removing a product from the cart page")
    public void testRemoveFromCart() {
        loginWithValidUser();
        productsPage.addFirstToCart();
        productsPage.clickCart();
        cartPage.removeFirstItem();
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Test(priority = 7, description = "Verify 'Continue Shopping' button functionality")
    public void testContinueShopping() {
        loginWithValidUser();
        productsPage.clickCart();
        cartPage.clickContinue();
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Test(priority = 8, description = "Verify sorting products by Name (Z to A)")
    public void testSortZtoA() {
        loginWithValidUser();
        productsPage.selectSort("Name (Z to A)");
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Test(priority = 9, description = "Verify sorting products by Price (Low to High)")
    public void testSortPriceLowToHigh() {
        loginWithValidUser();
        productsPage.selectSort("Price (low to high)");
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Test(priority = 10, description = "Verify sorting products by Price (High to Low)")
    public void testSortPriceHighToLow() {
        loginWithValidUser();
        productsPage.selectSort("Price (high to low)");
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Test(priority = 11, description = "Verify mandatory field validation on Checkout")
    public void testCheckoutEmptyForm() {
        loginWithValidUser();
        productsPage.addFirstToCart();
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorVisible());
        Assert.assertTrue(checkoutPage.getErrorText().contains("First Name is required"));
    }
    @Test(priority = 12, description = "Verify ZIP code validation on Checkout")
    public void testCheckoutZipMissing() {
        loginWithValidUser();
        productsPage.addFirstToCart();
        productsPage.clickCart();
        cartPage.clickCheckout();

        // Input data but leave Zip empty
        checkoutPage.fillInfo("Selman", "Karatas", "");
        checkoutPage.clickContinue();

        // Verification
        Assert.assertTrue(checkoutPage.isErrorVisible(), "Validation error should be visible");
        Assert.assertTrue(checkoutPage.getErrorText().contains("Postal Code is required"),
                "Error text mismatch! Found: " + checkoutPage.getErrorText());
    }
}