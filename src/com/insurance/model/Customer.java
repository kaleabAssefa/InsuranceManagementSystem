package com.insurance.model;

/**
 * The Customer - registers, buys policies and views the policies they hold.
 */
public class Customer extends User {

    private String phoneNumber;

    public Customer(int id, String name, String email, String password, String phoneNumber) {
        super(id, name, email, password);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    @Override
    public String toString() {
        return super.toString() + " | Phone: " + phoneNumber;
    }
}
