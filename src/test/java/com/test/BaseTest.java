package com.test;

import com.test.pages.GooglePage;
import com.test.e2e.E2eSetupEnvironment;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners({
        TestOrderRandomizer.class
})
@Slf4j
public class BaseTest implements IHookable {

    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> WEB_DRIVER_WAIT_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<GooglePage> TEST_PAGE_THREAD_LOCAL = new ThreadLocal<>();

    @BeforeMethod
    @Parameters({"url"})
    public void setUp(String url) {
        WebDriver remoteWebDriver;
        String browser = System.getenv("BROWSER");
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
        WEB_DRIVER_THREAD_LOCAL.set(remoteWebDriver);
        WEB_DRIVER_WAIT_THREAD_LOCAL.set(new WebDriverWait(remoteWebDriver, 4));
        TEST_PAGE_THREAD_LOCAL.set(new GooglePage(System.getenv("LANGUAGE"), getWebDriver(), getWebDriverWait(), url));
        }

    @AfterMethod
    public void tearDown() {
        log.info("tearing down things for thread: {}", Thread.currentThread().getName());
        TEST_PAGE_THREAD_LOCAL.remove();
    }

    public GooglePage getTestPage() {
        return TEST_PAGE_THREAD_LOCAL.get();
    }

    private static WebDriver getWebDriver() {
        return WEB_DRIVER_THREAD_LOCAL.get();
    }

    private static WebDriverWait getWebDriverWait() {
        return WEB_DRIVER_WAIT_THREAD_LOCAL.get();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
