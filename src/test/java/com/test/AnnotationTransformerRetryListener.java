package com.test;

import lombok.extern.slf4j.Slf4j;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformerRetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    @Slf4j
    public static class RetryAnalyzer implements IRetryAnalyzer {

        private static final int MAX_RETRY_COUNT = 3;
        private int count = MAX_RETRY_COUNT;

        @Override
        public boolean retry(ITestResult result) {
            boolean retryAgain = false;
            if (count > 0) {
                log.warn("Going to retry test case: " + result.getMethod() + ", " + ((
                        (MAX_RETRY_COUNT - count) + 1)) + " out of " + MAX_RETRY_COUNT);
                retryAgain = true;
                --count;
            }
            return retryAgain;
        }
    }
}
