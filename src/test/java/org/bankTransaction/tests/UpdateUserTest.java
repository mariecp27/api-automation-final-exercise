package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class UpdateUserTest extends BaseTest {

    private int userId = 1;

    /**
     * Allows to update the {@link org.bankTransaction.pojo.User} information in the endpoint and verify the resulted Http Response Code.
     * @param endpoint String
     */
    @Parameters({"endpoint"})
    @Test
    public void updateUserTest(String endpoint) {
        Reporter.info("Verifying the user was successfully updated");
        Assert.assertEquals(updateUser(endpoint, userId), 200, "The user was not updated");
    }
}
