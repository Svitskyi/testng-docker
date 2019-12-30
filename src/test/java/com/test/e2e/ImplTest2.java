package com.test.e2e;


import com.test.pages.BasePageObject;
import com.test.pages.GooglePage;
import com.test.pages.GoogleSearchResultsPage;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class ImplTest2 extends BaseE2eTest {


    @Test(dataProvider = "keywords")
    @Description("This is a nice test with numbers")
    public void googlePageTestsearch_keywords_numbers(String keyword) {
        int i = getTestPage().hashCode();
        log.info("Thread: {} PageHashCode: {}, Browser: {}", Thread.currentThread().getName(), getTestPage().hashCode(), environment.getProperty("BROWSER"));
        GooglePage testPage = getTestPage();
        Assert.assertEquals(i, testPage.hashCode());
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
