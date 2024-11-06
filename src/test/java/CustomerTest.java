import org.example.Account;
import org.example.AccountType;
import org.example.Customer;
import org.example.CustomerType;
import org.example.Money;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void testWithdrawPersonWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(-22.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34.0);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10.0);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34.0);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10.0);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getAmount(), is(-20.25));
    }

    private Customer getPersonWithAccount(boolean premium) {
        AccountType accountType = new AccountType(premium);
        Money balance = new Money(34.0, "EUR");
        Account account = new Account(accountType, 9, balance);
        Customer customer = getPersonCustomer(account);
        account.setIban("RO023INGB434321431241");
        return customer;
    }

    private Account getAccountByTypeAndMoney(boolean premium, double money) {
        AccountType accountType = new AccountType(premium);
        Money balance = new Money(money, "EUR");
        Account account = new Account(accountType, 9, balance);
        account.setIban("RO023INGB434321431241");
        return account;
    }

    private Customer getPersonCustomer(Account account) {
        return new Customer("danix", "dan", "dan@mail.com", CustomerType.PERSON, account, 1.0);
    }

    private Customer getCompanyCustomer(Account account) {
        return new Customer("company", "", "company@mail.com", CustomerType.COMPANY, account, 0.50);
    }
}