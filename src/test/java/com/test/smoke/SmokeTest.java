package com.test.smoke;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SmokeTest {

    @Test
    @Parameters({"url"})
    public void testSmoke(String url) {
        System.out.println(String.format("smoke test url %s", url));
    }


}
