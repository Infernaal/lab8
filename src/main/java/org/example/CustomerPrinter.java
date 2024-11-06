package org.example;

public class CustomerPrinter {

    private final Customer customer;
    private final AccountService accountService;

    public CustomerPrinter(Customer customer, AccountService accountService) {
        this.customer = customer;
        this.accountService = accountService;
    }

    public String printCustomerDetails() {
        return customer.getFullName() + " " + getAccountDetails();
    }

    private String getAccountDetails() {
        Account account = accountService.getAccount();
        return "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney()
                + ", Days Overdrawn: " + account.getDaysOverdrawn()
                + ", Account type: " + account.getType();
    }
}