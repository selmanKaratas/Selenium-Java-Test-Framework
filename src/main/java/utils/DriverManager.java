package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Class to initialize and terminate the WebDriver
public class DriverManager {

    private static WebDriver driver;

    // Initialize Chrome
    public static void initializeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // open in incognito mode
        options.addArguments("--start-maximized"); // start maximized

        driver = new ChromeDriver(options);
        System.out.println("Chrome started");
    }

    // Get the Driver instance
    public static WebDriver getDriver() {
        return driver;
    }

    // Terminate the browser
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}