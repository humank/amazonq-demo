package org.example;

public class CreditCard {
    private long cardNumber;
    private int expiryYear;
    private int expiryMonth;
    private int securityCode;

    public CreditCard(long cardNumber, int expiryYear, int expiryMonth, int securityCode) {
        this.cardNumber = cardNumber;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.securityCode = securityCode;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public int getSecurityCode() {
        return securityCode;
    }
}
