package org.example;

public class PaymentInfo {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String userId;

    public PaymentInfo(String cardNumber, String expiryDate, String cvv, String userId) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public String getUserId() {
        return userId;
    }
}
