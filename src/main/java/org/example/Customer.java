package org.example;

public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;


    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    // use only to create companies
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    private String getFullName() {
        return name + " " + surname;
    }

    public void withdraw(double amount, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't withdraw in currency: " + currency);
        }

        double overdraftFee = calculateOverdraftFee(amount);
        account.setMoney(account.getMoney() - amount - overdraftFee);
    }

    private double calculateOverdraftFee(double amount) {
        if (account.getMoney() >= 0) {
            return 0;
        }

        double feeMultiplier = 1.0;

        if (customerType == CustomerType.COMPANY) {
            feeMultiplier = account.getType().isPremium()
                    ? companyOverdraftDiscount / 2
                    : companyOverdraftDiscount;
        } else if (customerType == CustomerType.PERSON && account.getType().isPremium()) {
            feeMultiplier = 1.0;
        }

        return amount * account.overdraftFee() * feeMultiplier;
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

    public String printCustomerDaysOverdrawn() {
        String fullName = name + " " + surname + " ";
        String accountDescription = "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + accountDescription;
    }

    public String printCustomerMoney() {
        String fullName = name + " " + surname + " ";
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
        return fullName + accountDescription;
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoney() + ", Account type: " + account.getType();
    }
}
