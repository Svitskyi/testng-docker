package com.test;


import com.test.pages.GooglePage;
import com.test.pages.GoogleSearchResultsPage;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class ImplTest2 extends BaseTest {


    @BeforeMethod
    public void testSetup2() {
        log.info(String.format("setup from thread: %s | thread id: %s | browser env %s", Thread.currentThread().getName(), Thread.currentThread().getId(), System.getenv("BROWSER")));
    }

    @Test(dataProvider = "keywords")
    @Description("This is a nice test with numbers")
    public void googlePageTestsearch_keywords_numbers(String keyword) {
        log.info(String.format("thread name: %s | thread id %s browser env %s", Thread.currentThread().getName(), Thread.currentThread().getId(), System.getenv("BROWSER")));
        GooglePage testPage = getTestPage();
        log.info("This test class {} and testpage hashcode {}", this.getClass().getName(), testPage.hashCode());
        GoogleSearchResultsPage googleSearchResultsPage = testPage.googleSearch(keyword);
        Assert.assertTrue(googleSearchResultsPage.getPageTitle().contains(keyword), "Failing test message");
    }


    @DataProvider
    public Object[][] keywords() {
        return new Object[][]{
                {
                        "222222"
                },
                {
                        "2222222222222222"
                }
        };
    }
}
