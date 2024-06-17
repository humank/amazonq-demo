package org.example;

public class Invoice {
    private final double transactionAmount;
    private final CreditCard creditCard;

    public Invoice(CreditCard creditCard, double transactionAmount) {
        this.creditCard = creditCard;
        this.transactionAmount = transactionAmount;
    }
}
