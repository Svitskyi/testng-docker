package com.test.e2e;


import com.test.BaseTest;
import com.test.pages.GooglePage;
import com.test.pages.GoogleSearchResultsPage;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class ImplTest3 extends BaseE2eTest {



    @Test(dataProvider = "keywords")
    @Description("This is a nice test with symbols")
    public void googlePageTestsearch_keywords_symbols(String keyword) {
        int i = getTestPage().hashCode();
        log.info("Thread: {} PageHashCode: {}, Browser: {}", Thread.currentThread().getName(), getTestPage().hashCode(), System.getenv("BROWSER"));
        GooglePage testPage = getTestPage();
        Assert.assertEquals(i, testPage.hashCode());
        GoogleSearchResultsPage googleSearchResultsPage = testPage.googleSearch(keyword);
        Assert.assertTrue(googleSearchResultsPage.getPageTitle().contains(keyword), "Failing test message");
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
