package com.test.e2e;

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class TestWithRetry extends BaseE2eTest {

    volatile int count = 0;

    @Test
    @Description("This test will retry and fail")
    public void testWillRetry() {

        getTestPage().googleSearch("You see me retrying #" + count);
        if (++count > 3) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }
}


