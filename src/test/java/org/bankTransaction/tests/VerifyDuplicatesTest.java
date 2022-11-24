package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class VerifyDuplicatesTest extends BaseTest {

    /**
     * Allows to check there are no duplicate emails in the endpoint, verify the resulted Http Response Code.
     * @param endpoint String
     */
    @Parameters({"endpoint"})
    @Test
    public void verifyDuplicatesTest(String endpoint) {
        Reporter.info("Verifying the users were properly obtained from the endpoint");
        Assert.assertEquals(getAllUsersStatus(endpoint), 200, "Users were not obtained from endpoint");

        Reporter.info("Verifying there are no duplicate emails in the endpoint");
        Assert.assertTrue(verifyDuplicates(endpoint), "There are duplicate emails");
    }
}
