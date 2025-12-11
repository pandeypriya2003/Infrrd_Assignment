package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage {

    private By flightRows = By.cssSelector(".flight-row");

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<Flight> getAllFlights(){
        List<WebElement> rows = driver.findElements(flightRows);
        List<Flight> flights = new ArrayList<>();
        for(WebElement row : rows){
            String name = row.findElement(By.cssSelector(".airline")).getText();
            String priceText = row.findElement(By.cssSelector(".price")).getText();
            // price like $123 ; strip non-digits
            int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
            flights.add(new Flight(name, price, row));
        }
        return flights;
    }

    public List<Flight> getSortedByPriceAscending(){
        return getAllFlights().stream()
                .sorted(Comparator.comparingInt(Flight::getPrice))
                .collect(Collectors.toList());
    }

    public static class Flight {
        private String airline;
        private int price;
        private WebElement element;

        public Flight(String airline, int price, WebElement element) {
            this.airline = airline;
            this.price = price;
            this.element = element;
        }

        public String getAirline() {
            return airline;
        }

        public int getPrice() {
            return price;
        }

        public WebElement getElement() {
            return element;
        }

        @Override
        public String toString() {
            return String.format("%s - $%d", airline, price);
        }
    }
}
