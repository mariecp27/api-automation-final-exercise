# api-automation-final-exercise

## API link
- [Endpoint](https://637bbb6b6f4024eac217041a.mockapi.io/users)
- [Project](https://mockapi.io/projects/637bbb6b6f4024eac217041b)
- [Clone](https://mockapi.io/clone/637bbb6b6f4024eac217041b)

## Considerations
- All methods needed for the tests are in the "BaseTeset.java" file.
- The tests must be executed from the "Suite.xml" file, because of the endpoint being handled as a parameter.
- In general, the tests could take a lot of time at being performed due to the *mockAPI* response speed, which could drop at some moments during the day.
- [java-faker](https://github.com/DiUS/java-faker) dependency is being used for the random data generation.

### Empty endpoint test
If there are above 30 users to be deleted in the endpoint, the test might fail due to 429 status. However, the test will inform about which users were not deleted.

### Initialize pojo test
Since one of the users will have a duplicate email in purpose, 11 users are originally created with random data, in order to ensure 10 of them will be created in the endpoint.

### Verify duplicate test
No additional considerations are needed.

### Update user test
- By default, the user to be updated will be the one with id = 1; in case it is chosen another user to be updated, the attribute "userId" in "UpdateUserTest.java" file must be changed.
- It will fail if the endpoint is empty.
