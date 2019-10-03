package com.test;

import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.ISuiteListener;

@Slf4j
public class SetupEnvironment implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        log.info("env is setting up");
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("env is shutting down");

    }
}
