package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {
    private By title = By.className("title");
    private By cartBadge = By.className("shopping_cart_badge");
    private By addButtons = By.cssSelector("button[id^='add-to-cart']");
    private By sortDropdown = By.className("product_sort_container");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By logoutBtn = By.id("logout_sidebar_link");
    private By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) { super(driver); }

    public boolean isOnProductsPage() { return getText(title).equals("Products"); }

    public void addFirstToCart() {
        driver.findElements(addButtons).get(0).click();
    }

    public void addMultipleToCart(int count) {
        for(int i=0; i<count; i++) {
            driver.findElements(addButtons).get(i).click();
        }
    }

    public String getCartCount() { return isDisplayed(cartBadge) ? getText(cartBadge) : "0"; }

    public void selectSort(String option) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(option);
    }

    public void clickCart() { click(cartLink); }

    public void logout() {
        click(menuBtn);
        click(logoutBtn);
    }
}