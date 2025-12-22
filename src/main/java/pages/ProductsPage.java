package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPage extends BasePage {

    // Elements
    private By pageTitle = By.className("title");
    private By cartIcon = By.className("shopping_cart_link");
    private By allProducts = By.className("inventory_item");
    private By addToCartBtns = By.cssSelector("button[id^='add-to-cart']");
    private By cartBadge = By.className("shopping_cart_badge");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By logoutBtn = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Check if on products page
    public boolean isOnProductsPage() {
        try {
            String title = getText(pageTitle);
            return title.equals("Products");
        } catch (Exception e) {
            return false;
        }
    }

    // Get page title
    public String getPageTitle() {
        return getText(pageTitle);
    }

    // Get total number of products on the page
    public int getProductCount() {
        List<WebElement> products = driver.findElements(allProducts);
        return products.size();
    }

    // Add first product to cart
    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartBtns);
        if (buttons.size() > 0) {
            buttons.get(0).click();
        }
    }

    // Read the product count from the cart badge
    public String getCartCount() {
        try {
            return getText(cartBadge);
        } catch (Exception e) {
            return "0"; // Returns 0 if cart is empty and badge is missing
        }
    }

    // Click on cart icon
    public void clickCart() {
        click(cartIcon);
    }

    // Open side menu
    public void openMenu() {
        click(menuBtn);
        // Small wait for menu to animate
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Perform logout flow
    public void logout() {
        openMenu();
        click(logoutBtn);
    }
}