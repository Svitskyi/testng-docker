package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class GoogleSearchResultsPage extends BasePageObject {

    public GoogleSearchResultsPage(String language, WebDriver webDriver, WebDriverWait webDriverWait) {
        super(language, webDriver, webDriverWait);
    }

    @Step("Getting google search results page title")
    public String getPageTitle() {
        log.info("Getting search page resuls title");
        return getWebDriver().getTitle();
    }
}
