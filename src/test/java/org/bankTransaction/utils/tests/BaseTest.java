package org.bankTransaction.utils.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bankTransaction.pojo.User;
import org.bankTransaction.reporting.Reporter;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class BaseTest {

    public List<User> getAllUsers(String endpoint) {
        RestAssured.baseURI = endpoint;
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("");

        JsonPath jsonPathEvaluator = response.jsonPath();

        List<User> allUsers = new ArrayList<>();

        try {
            allUsers = jsonPathEvaluator.getList(".", User.class);
        } catch (Exception e) {
            Reporter.error(String.valueOf(e));
        }

        return allUsers;
    }

    public boolean deleteAllUsers(String endpoint) {
        List<Boolean> deletedUserStatus = new ArrayList<>();
        if (this.getAllUsers(endpoint).size() > 0) {
            this.getAllUsers(endpoint).stream().forEach(user -> {
                deletedUserStatus.add(user.deleteUser(endpoint) != 200);
            });
        }
        return !deletedUserStatus.contains(false);
    }

    /**
     * Allows to print a message from a set template for assertions handling.
     * @param description <em>String</em> describing which assertion is being performed
     * @param actualValue Value obedient as the test result
     * @param expectedValue Value expected to the test result
     */
    protected <T> void checkThat(String description, T actualValue, Matcher<? super T> expectedValue) {
        Reporter.info(format("Checking that " + description.toLowerCase() + "[Expectation: %s]", expectedValue));
        try {
            MatcherAssert.assertThat(actualValue, expectedValue);
        } catch (AssertionError e) {
            Reporter.error(format("Assertion Error: [%s]", e.getMessage().replaceAll("\n", "")));
        }
    }
}
