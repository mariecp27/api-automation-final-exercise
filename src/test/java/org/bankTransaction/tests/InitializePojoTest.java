package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class, extended from {@link org.bankTransaction.utils.tests.BaseTest}
 */
public class InitializePojoTest extends BaseTest {

    private int usersToBeCreated = 11;

    /**
     * Allows to add {@link org.bankTransaction.pojo.User} to the endpoint and verify the resulted Http Response Code.
     * @param endpoint String
     */
    @Parameters({"endpoint"})
    @Test
    public void initializePojoTest(String endpoint) {
        Reporter.info("Verifying all users were successfully created");
        Assert.assertTrue(createUsers(endpoint, usersToBeCreated), "Users creation failed");
    }
}
