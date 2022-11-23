package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;

public class UpdateUserTest extends BaseTest {

    @Parameters({"endpoint"})
    @Test
    public void verifyDuplicatesTest(String endpoint) {
        Reporter.info("Starting 'Update user test'");
        checkThat("User has been successfully updated", updateUser(endpoint, 1), is(200));
    }
}
