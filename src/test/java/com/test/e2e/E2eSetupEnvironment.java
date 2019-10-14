package com.test.e2e;

import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.ISuiteListener;

@Slf4j
public class E2eSetupEnvironment implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        log.info("e2e env is setting up");
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("e2e env is shutting down");
    }
}
