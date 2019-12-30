package com.test.pages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

//@AllArgsConstructor
@Component
@Slf4j
public abstract class BasePageObject {

    @Autowired
    private Environment environment;


    public String getLanguage() {
        return environment.getProperty("LANGUAGE");
    }

    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private WebDriver webDriver;

    @Autowired
    public BasePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Getter(value = AccessLevel.PROTECTED)
    private WebDriverWait webDriverWait;

    public void init() {
        log.info("in init");
        WEB_DRIVER_THREAD_LOCAL.set(webDriver);
    }

    public WebDriver getWebDriver() {
        WebDriver webDriver = WEB_DRIVER_THREAD_LOCAL.get();
        log.info("get webdriver with hash {}", webDriver.hashCode());
        return webDriver;
    }

    public void destroy() {
        log.info("in destroy");
        WEB_DRIVER_THREAD_LOCAL.remove();
    }

    @Autowired
    public void setWebDriverWait(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }
}