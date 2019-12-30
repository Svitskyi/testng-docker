package com.test;

import com.test.pages.GooglePage;
import com.test.pages.GoogleSearchResultsPage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan("com.test.pages")
@Slf4j
public class TestContext {

    @Autowired
    private Environment environment;

    @Bean
    @Scope("prototype")
    public WebDriver webDriver() {
        String browser = environment.getProperty("BROWSER");
        WebDriver remoteWebDriver;
        try {
            switch (browser) {
                case "chrome":
                    remoteWebDriver = new RemoteWebDriver(new URL("http://" + "selenium-hub" + ":4444/wd/hub"), new ChromeOptions());
                    break;
                case "firefox":
                    remoteWebDriver = new RemoteWebDriver(new URL("http://" + "selenium-hub" + ":4444/wd/hub"), new FirefoxOptions());
                    break;
                default:
                    throw new RuntimeException(String.format("Browser %s not supported", browser));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getMessage());
            throw new RuntimeException("setup error");
        }
        remoteWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("setting up things for thread: {}", Thread.currentThread().getName());
        return remoteWebDriver;
    }

    @Bean
    public WebDriverWait webDriverWait(WebDriver webDriver) {
        return new WebDriverWait(webDriver, 4);
    }


    @Bean(value = "GooglePage", initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public GooglePage googlePage() {
        return new GooglePage(webDriver(), googleSearchResultsPage());
    }

    @Bean(value = "GoogleSearchResultsPage", initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public GoogleSearchResultsPage googleSearchResultsPage() {
        return new GoogleSearchResultsPage(webDriver());
    }
}
