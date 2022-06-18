package com.test.e2e;


import com.test.pages.GooglePage;
import com.test.pages.GoogleSearchResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Slf4j
public class ImplTest extends BaseE2eTest {

    @Test(dataProvider = "keywords", threadPoolSize = 10, invocationCount = 100)
    @Description("This is a nice test with letters")
    public void googlePageTestsearch_keywords_letters(String keyword) {
        int i = getTestPage().hashCode();
        log.info("Thread: {} PageHashCode: {}, Browser: {}", Thread.currentThread().getName(), getTestPage().hashCode(), "chrome");
        GooglePage testPage = getTestPage();
        Assert.assertEquals(i, testPage.hashCode());
        GoogleSearchResultsPage googleSearchResultsPage = testPage.googleSearch(keyword);
        log.info("Page title is {}", googleSearchResultsPage.getPageTitle().contains(keyword));
        Assert.assertTrue(googleSearchResultsPage.getPageTitle().contains(keyword));
    }

    @DataProvider
    public Object[][] keywords() {
        return new Object[][]{
                {
                        "abc"
                }
        };
    }

}
