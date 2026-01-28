package tests;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utils.DriverManager;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "Sauce Demo Automation");
        extent.setSystemInfo("QA Engineer", "Selman Karatas");
    }

    @BeforeMethod
    public void setup(Method method) {
        // Gets the class name and description for a cleaner report
        String className = method.getDeclaringClass().getSimpleName();
        String description = method.getAnnotation(Test.class).description();

        test = extent.createTest("[" + className + "] - " + method.getName());
        test.assignCategory(className); // Adds category filtering to report
        if (!description.isEmpty()) { test.info("Objective: " + description); }

        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.log(Status.FAIL, "❌ TEST FAILED: " + result.getThrowable());
            test.addScreenCaptureFromBase64String(screenshot, "Failure Screenshot");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "✅ TEST PASSED");
        }
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void flush() { if (extent != null) extent.flush(); }

    protected void loginWithValidUser() {
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");
        test.log(Status.INFO, "Pre-condition: Successful login");
    }
}