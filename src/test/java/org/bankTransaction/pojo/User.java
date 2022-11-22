package org.bankTransaction.pojo;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class User {
    private int id;
    private String name;
    private String lastName;
    private int accountNumber;
    private float amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private String telephone;

    public User() {

    }

    public User(int id, String name, String lastName, int accountNumber, float amount, String transactionType, String email, boolean active, String country, String telephone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public float getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int deleteUser(String endpoint) {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete(endpoint + "/" + this.getId());

        return response.getStatusCode();
    }
}
