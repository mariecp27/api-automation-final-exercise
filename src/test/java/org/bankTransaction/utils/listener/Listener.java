package org.bankTransaction.utils.listener;

import org.bankTransaction.reporting.Reporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Class for printing messages either, when a test passes or fails.
 */
public class Listener implements ITestListener {

    /**
     * Print a message when a test starts.
     * @param result Current test
     */
    @Override
    public void onTestStart(ITestResult result) {
        Reporter.info("Starting test: " + result.getName());
    }

    /**
     * Print a message when a test passes.
     * @param result Current test
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.info("Test: " + result.getName() + " [PASSED]");
        System.out.println();
    }

    /**
     * Print a message when a test fails.
     * @param result Current test
     */
    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.error("Test: " + result.getName() + " [FAILED]");
        System.out.println();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
