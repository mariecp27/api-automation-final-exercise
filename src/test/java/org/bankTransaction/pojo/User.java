package org.bankTransaction.pojo;

/**
 * Class which contains all 'User' attributes and methods.
 */
public class User {
    private int id;
    private String name;
    private String lastName;
    private int accountNumber;
    private double amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private String telephone;

    /**
     * Default 'User' constructor, needed for later JSON deserialization to the API response.
     */
    public User() {

    }

    /**
     * 'User' constructor by using custom values for the class attributes.
     * @param name String
     * @param lastName String
     * @param accountNumber int
     * @param amount double
     * @param transactionType String, any of: "withdrawal","payment","invoice" or,"deposit"
     * @param email String
     * @param active boolean
     * @param country String
     * @param telephone String
     */
    public User(String name, String lastName, int accountNumber, double amount, String transactionType, String email, boolean active, String country, String telephone) {
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

    /**
     * Allow getting the 'User' id, which is automatically created by the endpoint.
     * @return 'User' id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Allow getting the 'User' first name.
     * @return 'User' first name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Allow getting the 'User' last name.
     * @return 'User' last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Allow getting the 'User' account number.
     * @return 'User' account number
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Allow getting the 'User' savings in their account.
     * @return 'User' savings
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Allow getting the type of current transaction:
     * <ul>
     *     <li>withdrawal</li>
     *     <li>payment</li>
     *     <li>invoice</li>
     *     <li>deposit</li>
     * </ul>
     * @return Type of current transaction
     */
    public String getTransactionType() {
        return this.transactionType;
    }

    /**
     * Allow getting the 'User' email.
     * @return 'User' email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Allow getting the 'User' account status.
     * @return true if the 'User' account is active, otherwise false
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Allows getting the 'User' country.
     * @return 'User' country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Allows getting the 'User' cellphone.
     * @return 'User' cellphone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Allows to update the 'User' first name.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Allows to update the 'User' last name.
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Allows to update the 'User' account number.
     * @param accountNumber int
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Allows to update the 'User' savings in their account.
     * @param amount int
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Allow to update the type of current transaction to one of the followings:
     * <ul>
     *     <li>withdrawal</li>
     *     <li>payment</li>
     *     <li>invoice</li>
     *     <li>deposit</li>
     * </ul>
     * @param transactionType String
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Allows to update the 'User' email.
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Allows to update the 'User' account status.
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Allows to update the 'User' country.
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Allows to update the 'User' cellphone.
     * @param telephone String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
