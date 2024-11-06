package org.example;
public class Account {

    private String iban;
    private AccountType type;
    private int daysOverdrawn;
    private Money balance;
    private Customer customer;

    public Account(AccountType type, int daysOverdrawn, Money balance) {
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
        this.balance = balance;
    }

    public double bankCharge() {
        return 4.5 + calculateOverdraftCharge();
    }

    private double calculateOverdraftCharge() {
        if (type.isPremium()) {
            return 10 + Math.max(0, (daysOverdrawn - 7) * 1.0);
        } else {
            return daysOverdrawn * 1.75;
        }
    }

    public double getOverdraftFeeRate(CustomerType customerType) {
        return type.isPremium() && customerType == CustomerType.COMPANY ? 0.10 : 0.20;
    }

    public void withdraw(double amount, String currency, CustomerType customerType, double discount) {
        if (!balance.getCurrency().equals(currency)) {
            throw new RuntimeException("Currency mismatch: Can't withdraw " + currency);
        }
        double overdraftFee = calculateOverdraftFee(amount, customerType, discount);
        balance.subtract(amount + overdraftFee);
    }

    private double calculateOverdraftFee(double amount, CustomerType customerType, double discount) {
        if (balance.getAmount() >= 0) return 0;
        return amount * getOverdraftFeeRate(customerType) * discount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public AccountType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Account IBAN: " + iban + ", Balance: " + balance + ", Account type: " + type;
    }
}