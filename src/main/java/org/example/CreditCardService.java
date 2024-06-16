package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreditCardService {
    private List<Map<String, String>> creditCardData;

    public CreditCardService() {
        // Default constructor
    }

    public CreditCardService(List<Map<String, String>> creditCardData) {
        this.creditCardData = creditCardData;
    }

    public boolean validateCreditCard(CreditCard creditCard) {
        // Validate credit card information
        return isCreditCardValid(creditCard);
    }

    public boolean verifyCreditStatus(CreditCard creditCard) {
        // Verify customer's credit status
        return getCreditCardData(creditCard).get("hasCredit").equalsIgnoreCase("true");
    }

    public String getValidationMessage(CreditCard creditCard) {
        if (!isCreditCardValid(creditCard)) {
            return "Card expired";
        }
        return "";
    }

    public boolean hasInsufficientFunds(CreditCard creditCard) {
        // Check for insufficient funds
        return getCreditCardData(creditCard).get("hasCredit").equalsIgnoreCase("false");
    }

    private boolean isCreditCardValid(CreditCard creditCard) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        return creditCard.getExpiryYear() > currentYear ||
                (creditCard.getExpiryYear() == currentYear && creditCard.getExpiryMonth() >= currentMonth);
    }

    private Map<String, String> getCreditCardData(CreditCard creditCard) {
        return creditCardData.stream()
                .filter(data -> data.get("cardNumber").equals(String.valueOf(creditCard.getCardNumber())))
                .findFirst()
                .orElse(null);
    }
}
