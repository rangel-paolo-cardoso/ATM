package com.rangel;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Transaction Test")
public class TransactionTest {
    
    @Test
    @DisplayName("Tests the constructor method of the Transaction class.")
    void constructorTest() {
        Transaction transaction = new Transaction(5_000.00, "Test");
        assertEquals(5_000.00, transaction.getAmount());
        assertEquals("Test", transaction.getDescription());
        assertNull(transaction.getInstant());
    }

    @Test
    @DisplayName("Tests the Getter method of the amount attribute.")
    void getAmountTest() {
        Transaction transaction = new Transaction(5_000.00, "Test");
        assertEquals(3_000.00, transaction.getAmount());
    }

    @Test
    @DisplayName("Tests the method Return Transaction Summary.")
    void returnTransactionSummaryTest() {
        Transaction transaction = new Transaction(3_000.00, "Test");
        assertTrue(transaction.returnTransactionSummary().contains(" -------- Test: R$3000.00 +"));
    }

    @Test
    @DisplayName("Tests if the method Instant is generating the instant correctly.")
    void returnInstantTest() {
        Transaction transaction = new Transaction(3_000.00, "Test");
        String instant = transaction.returnInstant();
        String comparacaoInstante = transaction.returnInstant();
        assertTrue(comparacaoInstante.split(":")[2].equals(instant.split(":")[2]));
    }
}
