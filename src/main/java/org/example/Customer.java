package org.example;

public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double overdraftDiscount;

    public Customer(String name, String surname, String email, CustomerType customerType, Account account, double overdraftDiscount) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
        this.overdraftDiscount = overdraftDiscount;
    }

    public void withdraw(double amount, String currency) {
        account.withdraw(amount, currency, customerType, overdraftDiscount);
    }

    public String getCustomerInfo() {
        return String.format("Customer: %s %s, Email: %s, %s", name, surname, email, account);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return getCustomerInfo();
    }
}