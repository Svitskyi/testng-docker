package com.test.it;

import com.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Slf4j
@Listeners({
        ItSetupEnvironment.class
})
public class ITTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeIt() {
        try {
            getTestPage().openPage();
        } catch (Exception | Error e) {
            log.info("Cannot open because we didnt really start IT env");
        }
    }

    @Test
    public void testIntegration() {
        log.info("Would open this url {} in it tests", getTestPage().getUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void afterIt() {
        try {
            getTestPage().closePage();
        } catch (Exception | Error e) {
            log.info("Nothing to close");
        }
    }

}
