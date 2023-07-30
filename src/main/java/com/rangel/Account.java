// package com.rangel;

// import java.util.ArrayList;

// public class Account {

//     private String accountType;
//     private String accountId;
//     private PersonCustomer personCustomer;

//     private ArrayList<Transaction> transactions;

//   /**
//    * Accounts Constructor.
//    * @param accountType String, it can be savings or checking.
//    * @param personCustomer PersonCustomer, represents the customer account owner.
//    * @param bank an instance used to initialize an account number.
//    */
//   public Account(String accountType, PersonCustomer personCustomer, Bank bank) {
//     this.accountType = accountType;
//     this.accountId = bank.generateNumberNewAccount();
//     this.transactions = new ArrayList<>();
//     this.personCustomer = personCustomer;
//   }
// }
