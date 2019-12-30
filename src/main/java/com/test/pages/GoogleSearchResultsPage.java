package com.test.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoogleSearchResultsPage extends BasePageObject {

    public GoogleSearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Getting google search results page title")
    public String getPageTitle() {
        log.info("Getting search page resuls title");
        return getWebDriver().getTitle();
    }
}
