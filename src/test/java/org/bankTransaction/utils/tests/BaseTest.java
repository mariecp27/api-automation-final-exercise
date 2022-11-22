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
                //System.out.println(user.deleteUser(endpoint));
                //System.out.println(user.getId());
                //System.out.println(endpoint + "/" + user.getId());
            });
        }
        return !deletedUserStatus.contains(false);
    }

    public boolean createUsers(String endpoint) {
        List<User> users = new ArrayList<>();
        List<Boolean> createdUserStatus = new ArrayList<>();

        users.add(new User("Oma","Heaney", 34653034, 100000, "withdrawal", getRandomEmail(users), true, "Guyana", "1-220-311-1020"));
        users.add(new User("Carolyne", "Kshlerin", 72825362, 150000, "deposit", getRandomEmail(users), true, "Malvinas", "562-835-3019"));
        users.add(new User("Columbus", "Rath", 94460438, 400000, "payment", getRandomEmail(users), true, "Cyprus", "645-970-8574"));
        users.add(new User("Keenan", "Vandervort", 20618260, 50000, "deposit", getRandomEmail(users), false, "Greece", "1-430-492-3278"));
        users.add(new User("Kaitlin", "Crona", 75757, 600000, "withdrawal", getRandomEmail(users), true, "Saint Vincent and the Grenadines", "1-817-421-0452"));
        users.add(new User("Ardith", "Runte", 3283764, 350000, "payment", getRandomEmail(users), true, "Greenland", "614-557-0937"));
        users.add(new User("Linnea", "Bartoletti", 38686193, 750000, "withdrawal", getRandomEmail(users), true, "Moldova", "893-277-7155"));
        users.add(new User("Noemy", "Borer", 71636841, 700000, "payment", getRandomEmail(users), false, "Malta", "993-276-7155"));
        users.add(new User("Meta", "Gutkowski", 21651829, 550000, "payment", getRandomEmail(users), true, "Angola", "1-205-912-6884"));
        users.add(new User("Chase", "Schoen", 71606385, 200000, "withdrawal", getRandomEmail(users), true, "French", "323-572-2440"));

        users.stream().forEach(user -> {
            createdUserStatus.add(user.createUser(endpoint, user) != 200);
        });

        return !createdUserStatus.contains(false);
    }

    public String getRandomEmail(List<User> users) {
       boolean isUser = false;
        String email = "";
       do {
           email = "email" + getRandomNumber() + "@email.com";
           for (int i = 0; i < users.size(); i++) {
               if (users.get(i).getEmail().equals(email)) {
                   isUser = true;
               }
           }
       } while (isUser);
       return email;
    }

    public int getRandomNumber() {
        return (int)(Math.random() * 1000);
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
