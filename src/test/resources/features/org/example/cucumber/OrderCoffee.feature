Feature: Order Coffee

  Scenario Outline: Customer orders coffee to-go
    Given the customer wants to order coffee to-go  
    When the customer orders an Americano
    Then the customer should receive a 10% discount
    And the total cost should be $9.00 USD  
    Examples:

  Scenario Outline: Customer orders coffee to dine-in
    Given the customer wants to dine-in
    When the customer orders a latte
    Then the total cost should be $<expectedCost> USD
    Examples:
      | expectedCost |
      | 12.00        |

  Scenario: Customer orders multiple coffees
    Given the customer wants to order coffee to-go
    When the customer orders 3 Americanos
    Then the customer should receive a 15% discount
    And the total cost should be $25.50 USD
    
  Scenario: Customer receives receipt
    Given the customer has placed an order
    When the order is successfully processed
    Then the customer should receive a receipt

  Scenario: Customer orders no coffee 
    Given the customer wants to order coffee to-go
    When the customer does not order any coffee
    Then the total cost should be $0.00 USD
    
  Scenario: Customer orders coffee with invalid type
    Given the customer wants to dine-in 
    When the customer orders an "Invalid Coffee"
    Then an error message should be displayed
