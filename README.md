SauceDemo UI Automation Framework

This is a fully functional automated testing suite for the SauceDemo (Swag Labs) website. It demonstrates practical automation skills using the Page Object Model (POM) pattern.



1. Framework Architecture

The framework is divided into three main layers:

- Core & Utilities: Handles browser setup and common actions. This is the core of the framework.
- Page Objects: Contains locators and page specific actions. Think of it as the library.
- Tests: Where the actual test scenarios and validations are implemented. This is the brain of the framework.

---

2. Components Breakdown

Core & Utilities

- DriverManager.java: Manages browser setup and configuration. Uses WebDriverManager to automatically handle drivers and sets ChromeOptions for a stable testing window.
- BasePage.java: Parent class for all page objects. Implements explicit waits to ensure elements are ready before interacting, preventing most common Selenium errors.

Page Objects

- Each page class (LoginPage, ProductsPage, etc.) mirrors the real web page.
- Locators are stored in one place for easy maintenance.
- Actions like login() or addProductToCart() simplify test logic and make tests readable.

Test Suite & Reporting

- BaseTest.java: Manages test setup and teardown. Launches a fresh browser for each test and cleans up afterward. Automatically captures screenshots on failure.
- ExtentReports: Generates an HTML report with logs, timestamps, and screenshots.

---

3. Test Scenarios (16 Key Points)

Category       | What We Test
-------------- | ----------------------------------------
Login          | Valid, invalid, locked credentials and required fields
Shopping       | Adding/removing items, cart badge synchronization, continue shopping
Sorting        | Product sorting by Name (A-Z/Z-A) and Price (Low-High/High-Low)
Checkout       | End-to-end purchase flow and form validation (ZIP, First Name)
Security       | Logout verification and back-button restriction
Failure Demo   | Intentional failure to demonstrate automatic screenshot capture

---

4. How to Run

Prerequisites: Java 18+ and Maven installed

Running Tests:
- Terminal: mvn clean test
- IntelliJ: Right-click testng.xml → Run

Viewing Reports: Open target/ExtentReport.html in a browser

---

5. Future Plans

- Parallel Execution: Run tests on multiple browsers at the same time
- CI/CD Integration: Automate test runs on every GitHub commit
- Data-Driven Testing: Move test data to JSON/Excel files for easier maintenance

---

Developed by Selman Karatas  
QA Presentation 2026
