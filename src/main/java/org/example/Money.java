package org.example;
public class Money {
    private double amount;
    private String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void subtract(double value) {
        this.amount -= value;
    }

    public Money applyDiscount(double rate) {
        return new Money(this.amount * rate, this.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}