package com.rangel;

import java.util.ArrayList;

public class PersonCustomer {

    private String fullName;
    private String cpf;
    private String password;

    private ArrayList<Account> accounts = new ArrayList<>();

    /**
     * PersonCustomer Constructor.
     * @param fullName the customer's full name.
     * @param cpf the number that identifies the customer.
     * @param password to access the system.
     */
    public PersonCustomer(String fullName, String cpf, String password) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.password = password;
        System.out.println("PersonCustomer object successfully instantiated!");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String returnSpecificAccountId(int index) {
        return accounts.get(index).getAccountId();
    }

    public int returnNumberOfAccounts() {
        return accounts.size();
    }

    public double returnSpecificAccountBalance(int index) {
        return accounts.get(index).returnBalance();
    }

    public void returnSpecificAccountStatement(int index) {
        accounts.get(index).returnBalanceStatement();
    }

    public void addSpecificAccountTransaction(int index, double amount, String description) {
        accounts.get(index).addTransaction(amount, description);
    }

    public boolean validatePassword(String password) {
        return password.equals(this.password);
    }

    /**
     * Prints a summary of all the customer's accounts.
     */
    public void returnAccountsSummary() {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ") " + accounts.get(i).returnAccountSummary());
        }
    }
}
