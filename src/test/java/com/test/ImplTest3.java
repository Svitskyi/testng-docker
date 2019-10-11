package com.test;


import com.test.pages.GooglePage;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class ImplTest3 extends BaseTest {


    @BeforeMethod
    public void testSetup3() {
        log.info(String.format("setup from thread: %s | thread id: %s | browser env %s", Thread.currentThread().getName(), Thread.currentThread().getId(), System.getenv("BROWSER")));
    }

    @Test(dataProvider = "keywords")
    @Description("This is a nice test with symbols")
    public void googlePageTestsearch_keywords_symbols(String keyword) {
        log.info(String.format("thread name: %s | thread id %s browser env %s", Thread.currentThread().getName(), Thread.currentThread().getId(), System.getenv("BROWSER")));
        GooglePage testPage = getTestPage();
        log.info("This test class {} and testpage hashcode {}", this.getClass().getName(), testPage.hashCode());
        Assert.assertTrue(testPage.googleSearch(keyword).getPageTitle().contains(keyword), "Oh no");
    }


    @DataProvider
    public Object[][] keywords() {
        return new Object[][]{
                {
                        "#!@$!@$"
                },
                {
                        "*(&(*^("
                }
        };
    }

}
