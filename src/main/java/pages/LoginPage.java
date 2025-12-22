package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Page URL
    private String loginUrl = "https://www.saucedemo.com/";

    // Page elements - locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Navigate to login page
    public void goToLoginPage() {
        navigateToUrl(loginUrl);
    }

    // Enter username
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    // Enter password
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    // Click login button
    public void clickLoginButton() {
        click(loginBtn);
    }

    // Perform complete login flow
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // Read error message text
    public String getErrorMessage() {
        return getText(errorMsg);
    }

    // Check if error message is displayed
    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMsg);
    }

    // Verify if current page is login page
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().equals(loginUrl);
    }
}