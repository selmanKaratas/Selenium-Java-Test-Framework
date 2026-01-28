package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By checkoutBtn = By.id("checkout");
    private By removeBtn = By.cssSelector("button[id^='remove']");
    private By continueShopping = By.id("continue-shopping");

    public CartPage(WebDriver driver) { super(driver); }

    public void clickCheckout() { click(checkoutBtn); }
    public void removeFirstItem() { click(removeBtn); }
    public boolean isCartEmpty() { return !isDisplayed(removeBtn); }
    public void clickContinue() { click(continueShopping); }
}