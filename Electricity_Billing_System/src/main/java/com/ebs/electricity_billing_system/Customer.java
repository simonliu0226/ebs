package com.ebs.electricity_billing_system;

/**
 * @author Simon
 */
public class Customer {
    // Instance variables
    private final String name;
    private final Integer meter_num;
    private final String address;
    private final String city;
    private final String state;
    private final String email;
    private final Long phone;
    private String status;
    private Double balance;

    // Constructor
    public Customer(String name, Integer meter_num, String address, String city, String state, String email, Long phone, String status, Double balance) {
        this.name = name;
        this.meter_num = meter_num;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.balance = balance;
    }

    // Setter and getter methods for Customer class
    public String getName() {
        return name;
    }

    public Integer getMeter_num() {
        return meter_num;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
