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
        /**
         * |---------------------|---------|--------|-------------|------------------------------------------|
         * | FailedWithinSuccess | Skipped | Failed | Status Code | Remarks                                  |
         * |---------------------|---------|--------|-------------|------------------------------------------|
         * | 0                   | 0       | 0      | 0           | Passed tests                             |
         * | 0                   | 0       | 1      | 1           | Failed tests                             |
         * | 0                   | 1       | 0      | 2           | Skipped tests                            |
         * | 0                   | 1       | 1      | 3           | Skipped/Failed tests                     |
         * | 1                   | 0       | 0      | 4           | FailedWithinSuccess tests                |
         * | 1                   | 0       | 1      | 5           | FailedWithinSuccess/Failed tests         |
         * | 1                   | 1       | 0      | 6           | FailedWithinSuccess/Skipped tests        |
         * | 1                   | 1       | 1      | 7           | FailedWithinSuccess/Skipped/Failed tests |
         * |---------------------|---------|--------|-------------|------------------------------------------|
         */
        //TestNG will exit zero on all passing tests. On retry exit code will be 2
//        getTestPage().googleSearch("You see me retrying #" + count);
//        if (++count > 3) {
//            Assert.assertTrue(true);
//        } else {
//            Assert.fail();
//        }
    }
}


