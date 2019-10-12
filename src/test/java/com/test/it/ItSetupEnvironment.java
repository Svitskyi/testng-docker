package com.test.it;

import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.ISuiteListener;

@Slf4j
public class ItSetupEnvironment implements ISuiteListener {


    @Override
    public void onStart(ISuite suite) {
        log.info("it env is setting up");
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("it env is shutting down");
    }
}
