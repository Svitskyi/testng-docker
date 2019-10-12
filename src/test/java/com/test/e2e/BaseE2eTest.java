package com.test.e2e;

import com.test.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({
        E2eSetupEnvironment.class
})
public class BaseE2eTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void before() {
        getTestPage().openPage();
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        getTestPage().closePage();
    }
}
