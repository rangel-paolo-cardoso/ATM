package com.rangel;

import java.util.ArrayList;

public class Account {

    private String accountType;
    private String accountId;
    private PersonCustomer personCustomer;

    private ArrayList<Transaction> transactions;

    /**
     * Accounts Constructor.
     * @param accountType String, it can be savings or checking.
     * @param personCustomer PersonCustomer, represents the customer account owner.
     * @param bank an instance used to initialize an account number.
     */
    public Account(String accountType, PersonCustomer personCustomer, Bank bank) {
        this.accountType = accountType;
        this.accountId = bank.generateNumberNewAccount();
        this.transactions = new ArrayList<>();
        this.personCustomer = personCustomer;
    }

    public void addTransaction(double amount, String description) {
        transactions.add(new Transaction(amount, description));
    }

    /**
     * Returns the account balance, adding up all the transactions.
     * @return a double, which is the total of the present balance.
     */
    public double returnBalance() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(0.0, (increment, next) -> increment + next);
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    /**
    * PersonCustomer Getter.
     * @return retorna PessoaCliente dona da conta.
     */
    public PersonCustomer getPersonCustomer() {
        return personCustomer;
    }

    /**
     * Prints the account balance statement.
     */
    public void returnBalanceStatement() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).returnTransactionSummary());
        }
    }

    /**
     * Shows a summary of the account data.
     * @return a String with the balance saldo, account type and account id.
     */
    public String returnAccountSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append(accountId);
        summary.append(" : R$");
        summary.append(String.format("%.2f", returnBalance()));
        summary.append(" : ");
        summary.append(accountType);
        return summary.toString();
    }
}
