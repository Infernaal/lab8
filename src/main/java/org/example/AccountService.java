package org.example;

public class AccountService {

    private final Account account;

    public AccountService(Account account) {
        this.account = account;
    }

    public void withdraw(Customer customer, double amount, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new IllegalArgumentException("Can't withdraw in currency: " + currency);
        }

        double overdraftFee = calculateOverdraftFee(customer, amount);
        account.setMoney(account.getMoney() - amount - overdraftFee);
    }

    private double calculateOverdraftFee(Customer customer, double amount) {
        if (account.getMoney() >= 0) {
            return 0;
        }

        double feeMultiplier = 1.0;

        if (customer.getCustomerType() == CustomerType.COMPANY) {
            feeMultiplier = account.getType().isPremium()
                    ? customer.getCompanyOverdraftDiscount() / 2
                    : customer.getCompanyOverdraftDiscount();
        } else if (customer.getCustomerType() == CustomerType.PERSON && account.getType().isPremium()) {
            feeMultiplier = 1.0;
        }

        return amount * account.overdraftFee() * feeMultiplier;
    }

    public String getAccountDetails(Customer customer) {
        return customer.getFullName() + " Account: IBAN: " + account.getIban()
                + ", Money: " + account.getMoney()
                + ", Days Overdrawn: " + account.getDaysOverdrawn()
                + ", Account type: " + account.getType();
    }
}