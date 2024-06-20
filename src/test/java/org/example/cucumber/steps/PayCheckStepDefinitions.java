package org.example.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.example.CreditCard;
import org.example.CreditCardService;
import org.example.Invoice;

import java.util.List;
import java.util.Map;

public class PayCheckStepDefinitions {

    private CreditCard creditCard;
    private CreditCardService creditCardService;
    private boolean isCardValid;
    private String validationMessage;

    @Given("the following credit cards exist in the system:")
    public void theFollowingCreditCardsExistInTheSystem(DataTable dataTable) {
        List<Map<String, String>> creditCardData = dataTable.asMaps(String.class, String.class);
        // Initialize credit card service with the provided credit card data
        creditCardService = new CreditCardService(creditCardData);
    }

    @Given("the customer enters a {long} card number, expiry year {int}, month {string}, and security code {int}")
    public void the_customer_enters_a_card_number_expiry_year_month_and_security_code(Long cardNumber, Integer expiryYear, String expiryMonth, Integer securityCode) {
        creditCard = new CreditCard(cardNumber, expiryYear, Integer.parseInt(expiryMonth), securityCode);
    }

    @When("the system checks the card number, expiry date, and security code")
    public void theSystemChecksTheCardNumberExpiryDateAndSecurityCode() {
        isCardValid = creditCardService.validateCreditCard(creditCard);
    }

    @Then("the system should validate the credit card information as correct")
    public void theSystemShouldValidateTheCreditCardInformationAsCorrect() {
        assert isCardValid;
    }

    @And("the system should confirm with the credit card center that the customer's credit status is healthy")
    public void theSystemShouldConfirmWithTheCreditCardCenterThatTheCustomersCreditStatusIsHealthy() {
        boolean hasCreditStatus = creditCardService.verifyCreditStatus(creditCard);
        assert hasCreditStatus;
    }

    @Then("the system should issue an invoice with transaction amount {double}")
    public void theSystemShouldIssueAnInvoice(double transactionAmount) {
        // Logic to issue an invoice
        Invoice invoice = CreditCardService.issueInvoice(creditCard,transactionAmount);
    }

    @And("the invoice should contain the transaction date, time, card number")
    public void theInvoiceShouldContainTheTransactionDateTimeCardNumber() {
        // Logic to verify invoice contents

    }

    @Then("the system should validate the credit card information as expired")
    public void theSystemShouldValidateTheCreditCardInformationAsExpired() {
        validationMessage = creditCardService.getValidationMessage(creditCard);
        assert validationMessage.equals("Card expired");
    }

    @And("the system should decline the transaction with an error message {string}")
    public void theSystemShouldDeclineTheTransactionWithAnErrorMessage(String errorMessage) {
        assert !isCardValid;
        assert validationMessage.contains(errorMessage);
    }

    @And("the system checks with the credit card center for the available funds")
    public void theSystemChecksWithTheCreditCardCenterForTheAvailableFunds() {
        boolean hasInsufficientFunds = creditCardService.hasInsufficientFunds(creditCard);
        assert hasInsufficientFunds;
    }

    @Then("the system should validate the credit card information as having insufficient funds")
    public void theSystemShouldValidateTheCreditCardInformationAsHavingInsufficientFunds() {
        validationMessage = creditCardService.getValidationMessage(creditCard);
        assert validationMessage.contains("Insufficient funds");
    }
}
