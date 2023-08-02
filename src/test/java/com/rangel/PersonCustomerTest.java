package com.rangel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("PersonCustomer Test")
public class PersonCustomerTest {

    @Test
    @DisplayName("Tests the PersonCustomer class constructor.")
    public void constructorTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream defaultOutput = System.out;
        System.setOut(printStream);

        new PersonCustomer("John", "00000000000", "12345");

        System.setOut(defaultOutput);
        assertTrue(outputStream.toString().contains("PersonCustomer object successfully instantiated!"));
    }

    @Test
    @DisplayName("Tests if the method Add Account and the method Return Number Of Accounts are working.")
    public void addAccountTestReturnNumberOfAccountsTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        assertEquals(0, customer.returnNumberOfAccounts());

        Account account = new Account("", customer, new Bank());
        customer.addAccount(account);

        assertEquals(1, customer.returnNumberOfAccounts());
    }

    @Test
    @DisplayName("Tests if the method return Specific Account Balance of the person customer.")
    public void returnSpecificAccountBalanceTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");

        Account account = new Account("", customer, new Bank());

        account.addTransaction(2_000, "Test 1");
        account.addTransaction(5_000, "Test 2");
        customer.addAccount(account);

        assertTrue(customer.returnSpecificAccountBalance(0) == 7_000);
    }

    @Test
    @DisplayName("Tests if the method Return Id Specific Account of the person customer.")
    public void returnSpecificAccountIdTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");

        Account account = new Account("", customer, new Bank());
        customer.addAccount(account);

        String accountId = customer.returnSpecificAccountId(0);
        assertTrue(customer.returnSpecificAccountId(0).equals(accountId));
    }

    @Test
    @DisplayName("Tests if the method Return Account Statement of the person customer.")
    public void returnSpecificAccountStatementTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream defaultOutput = System.out;
        System.setOut(printStream);

        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        Account account = new Account("Savings", customer, new Bank());
        account.addTransaction(5_000.00, "Test 1");
        customer.addAccount(account);

        customer.returnSpecificAccountStatement(0);

        System.setOut(defaultOutput);
        assertTrue(outputStream.toString().contains("-------- Test 1: R$5000,00"));
    }

    @Test
    @DisplayName("Tests if the method Add Specific Account Transaction of the person customer.")
    public void addSpecificAccountTransactionTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");

        Account account = new Account("Savings", customer, new Bank());
        customer.addAccount(account);

        assertTrue(customer.returnSpecificAccountBalance(0) == 0.0);

        customer.addSpecificAccountTransaction(0, 5_000.0, "Test 1");

        assertTrue(customer.returnSpecificAccountBalance(0) == 5_000.0);
    }

    @Test
    @DisplayName("Tests the method Validate Password.")
    public void validatePasswordTest() {
        String senha = "12345";
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");

        assertTrue(customer.validatePassword(senha));
    }

    @Test
    @DisplayName("Tests the method Return Accounts Summary.")
    public void returnAccountsSummaryTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream defaultOutput = System.out;
        System.setOut(printStream);

        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");

        Account account = new Account("Savings", customer, new Bank());
        customer.addAccount(account);
        customer.addSpecificAccountTransaction(0, 5_000.00, "Test 1");

        customer.returnAccountsSummary();

        System.setOut(defaultOutput);
        assertTrue(outputStream.toString().contains(": R$5000,00 : Savings"));
    }

    @Test
    @DisplayName("Tests if the Getter method of the attribute cpf is returning.")
    public void getCpfTest() {
        PersonCustomer customer = new PersonCustomer("John", "00000000000", "12345");
        assertTrue(customer.getCpf().equals("00000000000"));
    }
}
