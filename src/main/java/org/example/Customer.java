package org.example;

public class Customer {
    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private double companyOverdraftDiscount = 1.0;
    private AccountService accountService;

    public Customer(String name, String surname, String email, CustomerType customerType, AccountService accountService) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.accountService = accountService;
    }

    public Customer(String name, String email, AccountService accountService, double companyOverdraftDiscount) {
        this(name, "", email, CustomerType.COMPANY, accountService);
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double amount, String currency) {
        accountService.withdraw(this, amount, currency);
    }

    public String getFullName() {
        return name + (surname.isEmpty() ? "" : " " + surname);
    }

    public String printCustomerAccount() {
        return accountService.getAccountDetails(this);
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public CustomerType getCustomerType() { return customerType; }
    public double getCompanyOverdraftDiscount() { return companyOverdraftDiscount; }
}