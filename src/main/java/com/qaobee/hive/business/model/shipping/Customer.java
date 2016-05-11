package com.qaobee.hive.business.model.shipping;

/**
 * The type Customer.
 */
public class Customer {

    private String email;
    private String first_name; // NOSONAR
    private String last_name; // NOSONAR

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirst_name() { // NOSONAR
        return first_name;
    }

    /**
     * Sets first name.
     *
     * @param first_name the first name
     */
    public void setFirst_name(String first_name) { // NOSONAR
        this.first_name = first_name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLast_name() { // NOSONAR
        return last_name;
    }

    /**
     * Sets last name.
     *
     * @param last_name the last name
     */
    public void setLast_name(String last_name) { // NOSONAR
        this.last_name = last_name;
    }
}
