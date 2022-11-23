package org.bankTransaction.tests;

import org.bankTransaction.reporting.Reporter;
import org.bankTransaction.utils.tests.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;

public class VerifyDuplicatesTest extends BaseTest {
    @Parameters({"endpoint"})
    @Test
    public void verifyDuplicatesTest(String endpoint) {
        Reporter.info("Starting 'Verify duplicates test'");
        checkThat("There are not duplicate emails", verifyDuplicates(endpoint), is(true));
    }
}
