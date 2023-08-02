package com.rangel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Account Test")
public class AccountTest {

    @Test
    @DisplayName("Tests the constructor method of the class Account.")
    public void constructorTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());
        assertTrue(account.getAccountType().equals("Savings"));
        assertNotNull(account.getPersonCustomer());
    }

    @Test
    @DisplayName("Tests the method Add Transaction and returns the account balance.")
    public void addTransactionTestReturnBalanceTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());
        assertEquals(0, account.returnBalance());
        account.addTransaction(3_000, null);
        assertEquals(3_000, account.returnBalance());
    }

    @Test
    @DisplayName("Tests if the method Return Account Summary is returning a String with the values correctly.")
    public void returnAccountSummaryTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());
        account.addTransaction(5_000.00, "Test");

        assertTrue(account.returnAccountSummary().contains(": R$5000,00 : Savings"));
    }

    @Test
    @DisplayName("Tests if the method Return Balance Statement is printing the values correctly.")
    public void retornarExtratoTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream defaultOutput = System.out;
        System.setOut(printStream);

        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());
        account.addTransaction(5_000.00, "Test");

        account.returnBalanceStatement();

        System.setOut(defaultOutput);
        assertTrue(outputStream.toString().contains(" -------- Test: R$5000,00 +"));
    }

    @Test
    @DisplayName("Tests if the Getter method of the attribute accountId is returning.")
    public void getAccountIdTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());

        assertNotNull(account.getAccountId());
    }

    @Test
    @DisplayName("Tests if the Getter method of the attribute personCustomer is returning.")
    public void getPersonCustomerTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());

        assertNotNull(account.getPersonCustomer());
        assertTrue(account.getPersonCustomer().getFullName().equals("John"));
        assertTrue(account.getPersonCustomer().getCpf().equals("00000000000"));
    }
}
