package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class GooglePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public GooglePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Opening google page")
    public GooglePage openPage() {
        driver.get("https://www.google.com:443");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        log.info("Page opened");
        Thread.currentThread().setName(Thread.currentThread().getName() + " : " + String.valueOf(this.hashCode()).substring(0, 6));
        return this;
    }

    @Step("Closing google page")
    public void closePage() {
        driver.quit();
        log.info("Page closed");
    }

    @Step("Searching google using keyword {keyword}")
    public GoogleSearchResultsPage googleSearch(String keyword) {
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys(keyword);
        q.sendKeys(Keys.RETURN);
        return new GoogleSearchResultsPage(driver, wait);
    }


}
