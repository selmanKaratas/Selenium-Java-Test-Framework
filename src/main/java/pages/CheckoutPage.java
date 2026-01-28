package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipField = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By error = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) { super(driver); }

    public void fillInfo(String f, String l, String z) {
        type(firstNameField, f);
        type(lastNameField, l);
        type(zipField, z);
    }

    public void fillInfo() {
        fillInfo("Selman", "Karatas", "70736");
    }

    public void clickContinue() {
        click(continueBtn);
    }

    public String getErrorText() { return getText(error); }

    public boolean isErrorVisible() { return isDisplayed(error); }
}