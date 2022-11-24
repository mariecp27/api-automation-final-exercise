package org.bankTransaction.utils.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bankTransaction.pojo.User;
import org.bankTransaction.reporting.Reporter;

import java.util.*;

import static io.restassured.RestAssured.given;

/**
 * Base class for Test classes.
 */
public class BaseTest {

    protected int getUsersStatus;

    /**
     * Allows to get all users in the given endpoint and list them as objects from the class {@link org.bankTransaction.pojo.User}.
     * @param endpoint String
     * @return List of {@link org.bankTransaction.pojo.User} from the given endpoint
     */
    protected List<User> getAllUsers(String endpoint) {
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

        getUsersStatus = response.getStatusCode();

        return allUsers;
    }

    protected int getAllUsersStatus(String endpoint) {
        getAllUsers(endpoint);
        return getUsersStatus;
    }

    /**
     * Allows performing a DELETE request for a certain {@link org.bankTransaction.pojo.User} in the given endpoint.
     * @param endpoint String
     * @param user {@link org.bankTransaction.pojo.User}
     * @return Http Response Code for the DELETE request
     */
    protected int deleteUser(String endpoint, User user) {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete(endpoint + "/" + user.getId());

        return response.getStatusCode();
    }

    /**
     * Allows performing a POST request for a certain {@link org.bankTransaction.pojo.User} in the given endpoint.
     * @param endpoint String
     * @param user {@link org.bankTransaction.pojo.User}
     * @return Http Response Code for the POST request
     */
    protected int createUser(String endpoint, User user) {
        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(endpoint);

        return response.getStatusCode();
    }

    /**
     * Allows performing a PUT request for a certain {@link org.bankTransaction.pojo.User} in the given endpoint.
     * @param endpoint String
     * @param user {@link org.bankTransaction.pojo.User}
     * @return Http Response Code for the PUT request
     */
    protected int updateUser(String endpoint, User user) {
        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .put(endpoint + "/" + user.getId());

        return response.getStatusCode();
    }

    /**
     * Allows to delete all {@link org.bankTransaction.pojo.User} in a given endpoint.
     * @param endpoint String
     * @return true if all DELETE request were successful (Http Response Code: 200), otherwise false
     */
    protected boolean deleteAllUsers(String endpoint) {
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

    /**
     * Allow to create a certain amount of {@link org.bankTransaction.pojo.User} by using random data.
     * @param usersAmount int
     * @return List of created {@link org.bankTransaction.pojo.User}
     */
    protected List<User> createRandomUsers(int usersAmount) {
        List<User> users = new ArrayList<>();

        Faker javaFaker = Faker.instance(new Locale("en-US"));

        for (int i = 0; i < usersAmount; i++) {
            users.add(new User(
                    javaFaker.name().firstName(),
                    javaFaker.name().lastName(),
                    javaFaker.number().numberBetween(0,10000),
                    javaFaker.number().randomDouble(2, 100000, 100000000),
                    javaFaker.options().option("withdrawal","payment","invoice","deposit"),
                    javaFaker.internet().emailAddress(),
                    javaFaker.random().nextBoolean(),
                    javaFaker.country().name(),
                    javaFaker.phoneNumber().cellPhone()
                    ));
        }

        String duplicateEmail = javaFaker.internet().emailAddress();
        users.get(0).setEmail(duplicateEmail);
        users.get(usersAmount - 1).setEmail(duplicateEmail);

        return users;
    }

    /**
     * Allows to add a certain amount of random {@link org.bankTransaction.pojo.User} to a given endpoint.
     * @param endpoint String
     * @param usersAmount int
     * @return true if all POST request were successful (Http Response Code: 200), otherwise false
     */
    protected boolean createUsers(String endpoint, int usersAmount) {
        List<User> users = createRandomUsers(usersAmount);
        List<Boolean> createdUserStatus = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            boolean existingEmail = false;
            for (int j = 0; j < users.size() && !existingEmail; j++) {
                if (users.get(i).getEmail().equals(users.get(j).getEmail()) && i < j) {
                    Reporter.info("The email " + users.get(j).getEmail() + " is duplicate. User won't be created");
                    existingEmail = true;
                }
            }
            if (!existingEmail) {
                createdUserStatus.add(createUser(endpoint, users.get(i)) == 201);
            }
        }

        return !createdUserStatus.contains(false);
    }

    /**
     * Allows to verify is there are duplicate emails in a given endpoint.
     * @param endpoint String
     * @return true if there are no duplicate emails, otherwise false
     */
    protected boolean verifyDuplicates(String endpoint) {
        List<String> userEmails = new ArrayList<>();
        getAllUsers(endpoint).forEach(user -> {
            userEmails.add(user.getEmail());
        });

        Set<String> userSet = new HashSet<>(userEmails);

        return userSet.size() == getAllUsers(endpoint).size();
    }

    /**
     * Allows to update the information for a certain {@link org.bankTransaction.pojo.User} in a given endpoint.
     * @param endpoint String
     * @param userId int
     * @return Http Response Code of the PUT request
     */
    protected int updateUser(String endpoint, int userId) {
        User user = getAllUsers(endpoint).get(userId - 1);

        Faker javaFaker = Faker.instance(new Locale("en-US"));

        user.setName(javaFaker.name().firstName());
        user.setLastName(javaFaker.name().lastName());
        user.setAccountNumber(javaFaker.number().numberBetween(0,10000));
        user.setAmount(javaFaker.number().randomDouble(2, 100000, 100000000));
        user.setTransactionType(javaFaker.options().option("withdrawal","payment","invoice","deposit"));
        user.setEmail(javaFaker.internet().emailAddress());
        user.setActive(javaFaker.random().nextBoolean());
        user.setCountry(javaFaker.country().name());
        user.setTelephone(javaFaker.phoneNumber().cellPhone());

        return updateUser(endpoint,user);
    }
}
