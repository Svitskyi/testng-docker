package com.test.e2e;

import com.test.BaseTest;
import com.test.pages.GooglePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({
        E2eSetupEnvironment.class
})
@Slf4j
public class BaseE2eTest extends BaseTest {

    @Autowired
    protected Environment environment;

    @Autowired
    private GooglePage googlePage;

    ThreadLocal<GooglePage> threadLocal = new ThreadLocal<>();

    @Override
    protected GooglePage getTestPage() {
        return threadLocal.get();
    }


    @BeforeMethod(alwaysRun = true)
    public void before() {
        threadLocal.set(googlePage);
        getTestPage().openPage(environment.getProperty("URL"));
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
//        try {
//            getTestPage().closePage();
//        } finally {
//            threadLocal.remove();
//        }
    }
}
