package com.rangel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Bank Test")
public class BankTest {

    @Test
    @DisplayName("Tests the generator of unique number to a new account.")
    void generateNewAccountNumberTest() {
        Bank bank = new Bank();
        assertTrue(bank.generateNewAccountNumber() instanceof String);
        assertEquals(10, bank.generateNewAccountNumber().length());
    }

    @Test
    @DisplayName("Tests the Add Person Customer method, returning the object person customer.")
    void addPersonCustomerTest() {
        Bank bank = new Bank();
        String name = "John Test";
        String cpf = "00000000000";
        String password = "12345";
        assertTrue(bank.addPersonCustomer(name, cpf, password) instanceof PersonCustomer);
        assertTrue(bank.addPersonCustomer(name, cpf, password).getCpf().equals(cpf));
    }

    @Test
    @DisplayName("Tests the login method of the Person Customer, returning the object person customer correctly.")
    void personCustomerLoginTest() {
        Bank bank = new Bank();
        String name = "John";
        String cpf = "00000000000";
        String password = "12345";
        bank.addPersonCustomer(name, cpf, password);

        assertTrue(bank.personCustomerLogin(cpf, password) instanceof PersonCustomer);
        assertTrue(bank.personCustomerLogin(cpf, password).getCpf().equals(cpf));
        assertThrows(NoSuchElementException.class, () -> bank.personCustomerLogin("11111111", "12345"));
    }

    @Test
    @DisplayName("Tests if the Transfer Funds method is transferring correctly.")
    void depositTestTransferFundsTestShowBalanceStatementTest() {
        Bank bank = new Bank();
        bank.addPersonCustomer("John", "00000000000", "12345");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream defaultOutput = System.out;
        System.setOut(printStream);

        PersonCustomer loggedInCustomer = bank.personCustomerLogin("00000000000", "12345");
        Account savings = new Account("Savings", loggedInCustomer, bank);
        Account checking = new Account("Checking", loggedInCustomer, bank);

        loggedInCustomer.addAccount(savings);
        loggedInCustomer.addAccount(checking);

        bank.deposit(loggedInCustomer, 0, 10_000.00);
        bank.transferFunds(loggedInCustomer, 0, 1, 4_000.00);
        bank.showBalanceStatement(loggedInCustomer, 0);
        bank.showBalanceStatement(loggedInCustomer, 1);

        System.setOut(defaultOutput);
        assertTrue(outputStream.toString().contains(" -------- Deposit: R$10000"));
        assertTrue(outputStream.toString().contains(" -------- Withdraw: R$4000"));
        assertTrue(outputStream.toString().contains(" -------- Deposit: R$4000"));
    }

    @Test
    @DisplayName("Tests if the Withdraw method is working correctly.")
    void depositTestWithdrawTestShowBalanceStatementTest() {
        Bank bank = new Bank();
        bank.adicionarPessoaCliente("John", "00000000000", "12345");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream defaultOutput = System.out;
        System.setOut(printStream);

        PersonCustomer loggedInCustomer = bank.personCustomerLogin("00000000000", "12345");
        Account poupanca = new Account("Savings", loggedInCustomer, bank);

        loggedInCustomer.addAccount(poupanca);

        bank.deposit(loggedInCustomer, 0, 5_000.00);
        bank.withdraw(loggedInCustomer, 0, 2_000.00);

        bank.showBalanceStatement(loggedInCustomer, 0);

        System.setOut(defaultOutput);
        assertTrue(outputStream.toString().contains(" -------- Deposit: R$5000"));
        assertTrue(outputStream.toString().contains(" -------- Withdraw: R$2000"));
    }
}
