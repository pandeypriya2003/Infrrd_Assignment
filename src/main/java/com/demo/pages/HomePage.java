package com.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "source")
    private WebElement sourceInput;

    @FindBy(id = "destination")
    private WebElement destinationInput;

    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "searchBtn")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSource(String src){
        sourceInput.clear();
        sourceInput.sendKeys(src);
    }

    public void enterDestination(String dest){
        destinationInput.clear();
        destinationInput.sendKeys(dest);
    }

    // dateStr expected format: yyyy-MM-dd (mock site accepts this)
    public void selectDate(String dateStr){
        dateInput.clear();
        dateInput.sendKeys(dateStr);
    }

    public void clickSearch(){
        searchBtn.click();
    }
}
