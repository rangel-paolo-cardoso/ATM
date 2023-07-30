package com.rangel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private double amount;
    private String instant;
    private String description;

    /**
     * Transaction Constructor.
     * @param amount double, the amount used during a transaction.
     * @param description String, describes what was done.
     */
    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
        this.instant = returnInstant();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * A method that returns the instant in which a transaction was made.
    * @return String, with the date, time, minutes and seconds of the transaction moment.
    */
    public String returnInstant() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    /**
    * Returns the Transaction Summary.
    * @return String contendo informações sobre a transação.
    */
    public String returnTransactionSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append(instant);
        summary.append(" -------- ");
        summary.append(description);
        summary.append(": R$");
        summary.append(String.format("%.2f", amount));
        summary.append(" +");

        return summary.toString();
    }
}
