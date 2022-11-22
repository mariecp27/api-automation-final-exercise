package org.bankTransaction.tests;

import org.bankTransaction.utils.tests.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;

public class EmptyEndpointTest extends BaseTest {

    @Parameters({"endpoint"})
    @Test
    public void emptyEndpointTest(String endpoint) {
        deleteAllUsers(endpoint);
        checkThat("Users were successfully deleted if any", deleteAllUsers(endpoint), is(true));
        checkThat("Endpoint is empty", getAllUsers(endpoint).size(), is(0));
    }
}
