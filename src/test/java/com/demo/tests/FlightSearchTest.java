package com.demo.tests;

import com.demo.pages.HomePage;
import com.demo.pages.ResultsPage;
import com.demo.utils.WebDriverFactory;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightSearchTest {

    private WebDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    @BeforeClass
    public void setup(){
        driver = WebDriverFactory.createDriver();
    }

    @BeforeMethod
    public void loadHomePage(){
        // load the local mocksite from resources
        URL index = getClass().getResource("/mocksite/index.html");
        if(index == null){
            throw new RuntimeException("Mock site not found in resources. Make sure src/test/resources/mocksite/index.html exists.");
        }
        driver.get(index.toString());
        homePage = new HomePage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @Test
    public void searchAndFindCheapestFlights() throws InterruptedException {
        // Enter source and destination
        homePage.enterSource("City A");
        homePage.enterDestination("City B");

        // Select a date for next month (mock accepts yyyy-MM-dd)
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        String dateStr = nextMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        homePage.selectDate(dateStr);

        // Click Search
        homePage.clickSearch();

        // Wait briefly for JS to render results in mock site
        Thread.sleep(500);

        // Identify and print cheapest and second cheapest
        List<ResultsPage.Flight> sorted = resultsPage.getSortedByPriceAscending();
        Assert.assertTrue(sorted.size() >= 2, "Expected at least two flight results");

        System.out.println("All flights (sorted by price):");
        sorted.forEach(f -> System.out.println(f.toString()));

        ResultsPage.Flight cheapest = sorted.get(0);
        ResultsPage.Flight secondCheapest = sorted.get(1);

        System.out.println("Cheapest flight: " + cheapest);
        System.out.println("Second cheapest flight: " + secondCheapest);

        // Additional scenario: click the cheapest flight's row to 'view details' (mock opens alert)
        cheapest.getElement().click();

        // Open new tab in same session and navigate to Google
        var newTab = driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");
        System.out.println("Opened new tab and navigated to Google. Current URL: " + driver.getCurrentUrl());
    }

    @AfterClass(alwaysRun = true)
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }
}
