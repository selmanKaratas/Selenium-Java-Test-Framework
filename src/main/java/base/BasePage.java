package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// This class serves as the parent for all Page Objects.
// It centralizes common actions like clicking, typing, and explicit waits.
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Defaulting to a 10-second explicit wait for stability.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // A standard wrapper for finding elements.
    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // Waits for the element to be clickable before performing the action.
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Ensures the field is visible, clears any existing text, then types the new value.
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Fetches the visible text from the specified element.
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Returns true if the element is visible on the page; returns false if timed out or not found.
    protected boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Simple navigation method to load a specific URL.
    protected void navigateToUrl(String url) {
        driver.get(url);
    }
}