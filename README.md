# Selenium Hybrid Automation Framework

## Project Overview

Selenium Hybrid Automation Framework is a professional Java-based UI automation portfolio project for testing the [SauceDemo](https://www.saucedemo.com/) web application. It demonstrates a maintainable Selenium WebDriver framework using Page Object Model, TestNG, Maven, data-driven test data, configuration management, Extent Reports, automatic screenshots on failure, and GitHub Actions CI/CD.

This project is designed for SQA Engineers who want to showcase practical automation framework skills on GitHub.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model
- WebDriverManager
- Extent Reports
- GitHub Actions

## Framework Features

- Page Object Model with clean page classes
- Centralized locators inside page objects
- Config-driven browser, URL, and credential management
- Data-driven test values using properties files
- TestNG annotations and XML suite execution
- BaseTest class for browser setup and teardown
- Extent HTML report generation
- Automatic screenshot capture on test failure
- TestNG listener for reporting and failure handling
- Chrome browser support
- GitHub Actions workflow for CI execution
- Beginner-friendly code organization

## Folder Structure

```text
selenium-hybrid-automation-framework/
├── .github/
│   └── workflows/
│       └── selenium-tests.yml
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       │   └── BaseTest.java
│   │       ├── pages/
│   │       │   ├── LoginPage.java
│   │       │   ├── ProductsPage.java
│   │       │   ├── CartPage.java
│   │       │   └── CheckoutPage.java
│   │       ├── utilities/
│   │       │   ├── ConfigReader.java
│   │       │   ├── ScreenshotUtil.java
│   │       │   └── ExtentReportManager.java
│   │       └── listeners/
│   │           └── TestListener.java
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── LoginTest.java
│       │       ├── ProductTest.java
│       │       ├── CartTest.java
│       │       └── CheckoutTest.java
│       └── resources/
│           ├── config.properties
│           └── testdata.properties
├── screenshots/
├── reports/
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Test Scenarios

1. Successful login with valid user
2. Failed login with invalid user
3. Verify products page title
4. Verify product list is displayed
5. Add product to cart
6. Remove product from cart
7. Complete checkout flow
8. Verify order confirmation message
9. Logout successfully

## Installation Steps

1. Clone the repository:

```bash
git clone https://github.com/your-username/selenium-hybrid-automation-framework.git
```

2. Go to the project directory:

```bash
cd selenium-hybrid-automation-framework
```

3. Make sure Java and Maven are installed:

```bash
java -version
mvn -version
```

## How To Run Tests

Run the complete TestNG suite with Maven:

```bash
mvn clean test
```

To run in headless mode locally, update this value in `src/test/resources/config.properties`:

```properties
headless=true
```

## How To View Reports

After test execution, open the latest Extent Report from:

```text
reports/
```

Failure screenshots are saved in:

```text
screenshots/
```

TestNG default reports are generated in:

```text
target/surefire-reports/
```

## CI/CD Explanation

The GitHub Actions workflow in `.github/workflows/selenium-tests.yml` runs automatically on push and pull request events for the `main` and `master` branches. The workflow checks out the repository, sets up Java 17, installs Maven dependencies, sets up Chrome, runs `mvn clean test`, and uploads reports and screenshots as workflow artifacts.

## Author

Your Name

## GitHub Portfolio Note

This project is suitable for an SQA Engineer portfolio because it demonstrates practical automation framework design, clean test organization, reporting, screenshot handling, CI/CD integration, and real browser execution against a public demo application.
