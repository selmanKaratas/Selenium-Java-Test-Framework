package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;

    // Reporting objects
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        // Raporun kaydedileceği yer / Report save location
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "Sauce Demo Automation");
        extent.setSystemInfo("Tester", "Selman");
    }

    @BeforeMethod
    public void setUp(Method method) {
        // Her test metodunun ismini rapor başlığı yapar
        test = extent.createTest(method.getName());
        test.log(Status.INFO, "Test starting...");

        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Test sonucuna göre raporu güncelle / Update report based on test result
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            // Screenshot step will be added here next!
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed Successfully");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }

        DriverManager.quitDriver();
        System.out.println("Test finished");
    }

    @AfterSuite
    public void tearDownReport() {
        // Raporu dosyaya yaz / Flush the report to file
        extent.flush();
    }

    protected void loginWithValidUser() {
        loginPage.goToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
    }
}