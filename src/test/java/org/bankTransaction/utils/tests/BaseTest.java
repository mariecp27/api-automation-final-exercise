package org.bankTransaction.utils.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bankTransaction.pojo.User;
import org.bankTransaction.reporting.Reporter;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

import java.util.*;

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
        List<Integer> status = new ArrayList<>();
        List<User> users = getAllUsers(endpoint);
        if (users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                status.add(deleteUser(endpoint, users.get(i)));
                deletedUserStatus.add(status.get(i) == 200);
                if (status.get(i) != 200) {
                    Reporter.error("Cannot delete user with id " + users.get(i).getId() + " - Http Response Code: " + status.get(i));
                }
            }
        }
        return !deletedUserStatus.contains(false);
    }

    public boolean createUsers(String endpoint) {
        List<User> users = new ArrayList<>();
        List<Boolean> createdUserStatus = new ArrayList<>();

        Faker javaFaker = Faker.instance(new Locale("en-US"));

        for (int i = 0; i < 10; i++) {
            users.add(new User(
                    javaFaker.name().firstName(),
                    javaFaker.name().lastName(),
                    javaFaker.number().numberBetween(0,10000),
                    javaFaker.number().numberBetween(100000, 100000000),
                    getTransaction(),
                    javaFaker.internet().emailAddress(),
                    javaFaker.random().nextBoolean(),
                    javaFaker.country().name(),
                    javaFaker.phoneNumber().cellPhone()
                    ));
        }

        String duplicateEmail = javaFaker.internet().emailAddress();

        users.get(3).setEmail(duplicateEmail);
        users.get(8).setEmail(duplicateEmail);

        for (int i = 0; i < users.size(); i++) {
            boolean existingEmail = false;
            for (int j = 0; j < users.size() && !existingEmail; j++) {
                if (users.get(i).getEmail().equals(users.get(j).getEmail()) && i < j) {
                    existingEmail = true;
                }
            }
            if (!existingEmail) {
                createdUserStatus.add(createUser(endpoint, users.get(i)) != 200);
            }
        }

        return !createdUserStatus.contains(false);
    }

    public String getTransaction() {
        List<String> transactions = Arrays.asList("withdrawal", "deposit", "payment", "invoice");
        Random random = new Random();
        return transactions.get(random.nextInt(transactions.size()));
    }

    public boolean verifyDuplicates(String endpoint) {
        List<String> userEmails = new ArrayList<>();
        getAllUsers(endpoint).forEach(user -> {
            userEmails.add(user.getEmail());
        });

        Set<String> userSet = new HashSet<>(userEmails);

        return userSet.size() == getAllUsers(endpoint).size();
    }

    public int updateUser(String endpoint, int userId) {
        User user = getAllUsers(endpoint).get(userId - 1);

        user.setName("Rhiannon");
        user.setLastName("Weber");
        user.setAccountNumber(55181079);
        user.setAmount(850000);
        user.setTransactionType("deposit");
        user.setEmail("mathew_Ferry68@yahoo.com");
        user.setActive(true);
        user.setCountry("Cyprus");
        user.setTelephone("1-872-825-1820");

        return updateUser(endpoint,user);
    }

    public int deleteUser(String endpoint, User user) {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete(endpoint + "/" + user.getId());

        return response.getStatusCode();
    }

    public int createUser(String endpoint, User user) {
        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(endpoint);

        return response.getStatusCode();
    }

    public int updateUser(String endpoint, User user) {
        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .put(endpoint + "/" + user.getId());

        return response.getStatusCode();
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
