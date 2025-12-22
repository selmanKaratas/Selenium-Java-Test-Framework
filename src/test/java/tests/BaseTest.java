package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

// All test classes inherit from this class
public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;

    // Runs before each test method
    @BeforeMethod
    public void setUp() {
        System.out.println("Test starting...");

        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();

        // Initialize page objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    // Runs after each test method
    @AfterMethod
    public void tearDown() {
        System.out.println("Test finished");
        DriverManager.quitDriver();
    }

    // Helper method to login with valid credentials
    protected void loginWithValidUser() {
        loginPage.goToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
    }
}