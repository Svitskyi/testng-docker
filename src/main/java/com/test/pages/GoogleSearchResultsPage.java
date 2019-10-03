package com.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultsPage  {

    private WebDriver driver;
    private WebDriverWait wait;

    public GoogleSearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    @Step("Getting google search results page title")
    public String getPageTitle() {
        return driver.getTitle();
    }
}
