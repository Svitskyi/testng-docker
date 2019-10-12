package com.test.pages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
public abstract class BasePageObject {

    @Getter
    public String language;

    @Getter
    private WebDriver webDriver;
    @Getter(value = AccessLevel.PROTECTED)
    private WebDriverWait webDriverWait;

}
