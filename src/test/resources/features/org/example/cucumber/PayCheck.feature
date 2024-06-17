Feature: Credit Card Payment

  Background:
    Given the following credit cards exist in the system:
      | cardNumber       | expiryYear | expiryMonth | securityCode | hasCredit |
      | 1234567812345678 | 2025       | "12"        | 123          | true      |
      | 8765432187654321 | 2024       | "06"        | 456          | true      |
      | 1111222233334444 | 2023       | "09"        | 789          | false     |

  Scenario Outline: Customer uses a credit card for payment
    Given the customer enters a <cardNumber> card number, expiry year <expiryYear>, month <expiryMonth>, and security code <securityCode>
    When the system checks the card number, expiry date, and security code
    Then the system should validate the credit card information as correct
    And the system should confirm with the credit card center that the customer's credit status is healthy
    Then the system should issue an invoice with transaction amount <transactionAmount>
    And the invoice should contain the transaction date, time, card number

    Examples:
      | cardNumber       | expiryYear | expiryMonth | securityCode | transactionAmount |
      | 1234567812345678 | 2025       | "12"        | 123          | 500               |
      | 8765432187654321 | 2024       | "06"        | 456          | 500               |

  Scenario Outline: Customer tries to use an expired credit card
    Given the customer enters a <cardNumber> card number, expiry year <expiryYear>, month <expiryMonth>, and security code <securityCode>
    When the system checks the card number, expiry date, and security code
    Then the system should validate the credit card information as expired
    And the system should decline the transaction with an error message "Card expired"

    Examples:
      | cardNumber       | expiryYear | expiryMonth | securityCode |
      | 1234567812345678 | 2021       | "12"        | 123          |
      | 8765432187654321 | 2020       | "06"        | 456          |

  Scenario Outline: Customer tries to use a credit card with insufficient funds
    Given the customer enters a <cardNumber> card number, expiry year <expiryYear>, month <expiryMonth>, and security code <securityCode>
    When the system checks the card number, expiry date, and security code
    And the system checks with the credit card center for the available funds
    Then the system should validate the credit card information as having insufficient funds
    And the system should decline the transaction with an error message "Insufficient funds"

    Examples:
      | cardNumber       | expiryYear | expiryMonth | securityCode |
      | 1111222233334444 | 2023       | "09"        | 789          |
