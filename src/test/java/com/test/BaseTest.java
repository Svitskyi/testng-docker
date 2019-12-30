package com.test;

import com.test.pages.BasePageObject;
import com.test.pages.GooglePage;
import io.qameta.allure.Attachment;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Listeners({
        TestOrderRandomizer.class
})
@Slf4j
@ContextConfiguration(classes = TestContext.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests implements IHookable {

    protected abstract BasePageObject getTestPage();

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
        return ((TakesScreenshot) getTestPage().getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
