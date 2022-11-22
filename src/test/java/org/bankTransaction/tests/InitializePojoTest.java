package org.bankTransaction.tests;

import org.bankTransaction.utils.tests.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;

public class InitializePojoTest extends BaseTest {

    @Parameters({"endpoint"})
    @Test
    public void initializePojoTest(String endpoint) {
        checkThat("Users were correctly created", createUsers(endpoint), is(true));
    }
}
