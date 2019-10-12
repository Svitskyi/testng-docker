package com.test.e2e;


import com.test.BaseTest;
import com.test.pages.GooglePage;
import com.test.pages.GoogleSearchResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Slf4j
public class ImplTest extends BaseE2eTest {


    @Test(dataProvider = "keywords")
    @Description("This is a nice test with letters")
    public void googlePageTestsearch_keywords_letters(String keyword) {
        int i = getTestPage().hashCode();
        log.info("Thread: {} PageHashCode: {}, Browser: {}", Thread.currentThread().getName(), getTestPage().hashCode(), System.getenv("BROWSER"));
        GooglePage testPage = getTestPage();
        Assert.assertEquals(i, testPage.hashCode());
        GoogleSearchResultsPage googleSearchResultsPage = testPage.googleSearch(keyword);
        Assert.assertTrue(googleSearchResultsPage.getPageTitle().contains(keyword), "Failing test message");
    }

    @Test
    @Ignore("Just ignored test")
    @Issue("SWE-123")
    public void ignored() {

    }


    @DataProvider
    public Object[][] keywords() {
        return new Object[][]{
                {
                        "abc"
                },
                {
                        "adeasdsad"
                }
        };
    }

}
