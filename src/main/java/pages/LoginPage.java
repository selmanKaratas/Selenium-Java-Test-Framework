package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) { super(driver); }

    public void goTo() { driver.get("https://www.saucedemo.com/"); }

    public void login(String user, String pass) {
        type(usernameField, user);
        type(passwordField, pass);
        click(loginBtn);
    }

    public String getError() { return getText(errorMsg); }
    public boolean isOnLoginPage() { return isDisplayed(loginBtn); }
}