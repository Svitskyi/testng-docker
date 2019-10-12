package com.test.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class GooglePage extends BasePageObject {

    @Getter
    private String url;

    public GooglePage(@NonNull String language, @NonNull WebDriver webDriver, @NonNull WebDriverWait webDriverWait, @NonNull String url) {
        super(language, webDriver, webDriverWait);
        this.url=url;
    }

    @Step("Opening google page")
    public GooglePage openPage() {
        log.info("Opening google page in {} language", getLanguage());
        getWebDriver().get(url);
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        log.info("Page opened");
        return this;
    }

    @Step("Closing google page")
    public void closePage() {
        getWebDriver().quit();
        log.info("Page closed");
    }

    @Step("Searching google using keyword {keyword} and prompting for results with RETURN key")
    public GoogleSearchResultsPage googleSearch(String keyword) {
        log.info("Searching google for {} keyword", keyword);
        WebElement q = getWebDriver().findElement(By.name("q"));
        q.sendKeys(keyword);
        q.sendKeys(Keys.RETURN);
        return new GoogleSearchResultsPage(getLanguage(), getWebDriver(), getWebDriverWait());
    }

}
