package org.example;

public class Customer {
    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private double companyOverdraftDiscount = 1.0;

    public Customer(String name, String surname, String email, CustomerType customerType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
    }

    public Customer(String name, String email, double companyOverdraftDiscount) {
        this(name, "", email, CustomerType.COMPANY);
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public CustomerType getCustomerType() { return customerType; }
    public double getCompanyOverdraftDiscount() { return companyOverdraftDiscount; }

    public String getFullName() {
        return name + (surname.isEmpty() ? "" : " " + surname);
    }
}