package com.rangel;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private ArrayList<PersonCustomer> peopleCustomers = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Generates a new bank account number.
     * @return String, representing the account number.
     */
    public String generateNumberNewAccount() {
        int generationBase = 10;
        StringBuilder accountNumber = new StringBuilder();

        for (int n = 0; n < generationBase; n++) {
            accountNumber.append(String.valueOf(new Random().nextInt(10)));
        }
        return accountNumber.toString();
    }

    /**
    * Adds a new customer to the bank.
    * @param name String, representing the customer name.
    * @param cpf the customer's identification number.
    * @param password the code to access by logging in.
    * @return a PersonCustomer object that was added to the bank.
    */
    public PersonCustomer addPersonCustomer(String name, String cpf, String password) {
        PersonCustomer customer = new PersonCustomer(name, cpf, password);
        peopleCustomers.add(customer);
        return customer;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
    * Logs in the person customer.
    * @param cpf the identification number for logging in.
    * @param password the access password.
    * @return a PersonCustomer, which is the person logged in.
    */
    public PersonCustomer personCustomerLogin(String cpf, String password) {
        return peopleCustomers.stream()
                .filter(p -> p.getCpf().equals(cpf) && p.comparePassword(password))
                .findFirst()
                .get();
    }

    public void withdraw(PersonCustomer personCustomer, int fromAccount, double amount) {
        personCustomer.addSpecificAccountTransaction(fromAccount, amount, "Withdraw");
    }

    public void deposit(PersonCustomer personCustomer, int toAccount, double amount) {
        personCustomer.addSpecificAccountTransaction(toAccount, amount, "Deposit");
    }

    public void showBalanceStatement(PersonCustomer personCustomer, int account) {
        personCustomer.returnSpecificAccountStatement(account);
    }

    public void transferFunds(PersonCustomer cliente, int fromAccount, int toAccount, double amount) {
        withdraw(cliente, fromAccount, amount);
        deposit(cliente, toAccount, amount);
    }
}
