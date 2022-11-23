package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;

public class EmptyEndpointTest extends BaseTest {

    @Parameters({"endpoint"})
    @Test
    public void emptyEndpointTest(String endpoint) {
        Reporter.info("Starting 'Empty endpoint test'");
        checkThat("Endpoint is empty", getAllUsers(endpoint).size(), is(0));
        checkThat("Users were successfully deleted if any", deleteAllUsers(endpoint), is(true));
    }
}
