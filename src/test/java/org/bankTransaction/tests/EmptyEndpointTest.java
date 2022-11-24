package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class EmptyEndpointTest extends BaseTest {

    /**
     * Allows to check if the endpoint is empty and delete previous data is needed, verifying the resulted Http Response Code.
     * @param endpoint String
     */
    @Parameters({"endpoint"})
    @Test
    public void emptyEndpointTest(String endpoint) {
        Reporter.info("Verifying the endpoint is empty");
        Reporter.info("Records amount in the endpoint: " + getAllUsers(endpoint).size());

        Reporter.info("Verifying all previous data was successfully deleted if needed");
        Assert.assertTrue(deleteAllUsers(endpoint), "No all users were deleted");
    }
}
