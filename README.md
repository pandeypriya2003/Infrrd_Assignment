# Travel Automation Framework (POM) 

This project is a ready-to-run automation framework built with **Java + Maven + TestNG + Selenium** using the **Page Object Model (POM)** pattern.

## What it does
- Loads a small **local mock flight website** (included in `src/test/resources/mocksite/index.html`) to keep tests deterministic.
- Navigates to Flights section, enters source & destination, selects a date for next month, clicks Search.
- Identifies and prints the cheapest and second cheapest flights.
- Opens a new browser tab within the same session and navigates to Google.
- Additional scenario: Asserts at least two results are present and prints all results to console.

## How to run
1. Ensure Java 11+ and Maven are installed.
2. From project root run:
```bash
mvn clean test
```

The tests use WebDriverManager to manage the Chrome driver automatically. Make sure you have Chrome installed.

## Project structure highlights
- `src/main/java` : helper utilities (WebDriverFactory, WaitUtils)
- `src/main/java/com/demo/pages` : Page objects (BasePage, HomePage, ResultsPage)
- `src/test/java/com/demo/tests` : Test classes (FlightSearchTest)
- `src/test/resources/mocksite/index.html` : Local mock flight webpage used by tests

## Notes
- This mock-site approach ensures tests run consistently and remain testable without real travel sites (which often change DOMs or block automation).
- If you want to point the framework to a real travel website, update locators in page objects and the URL in the test.

Good luck! - Prepared for submission.
