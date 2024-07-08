Feature: Office Booking System

  Scenario: Search for Available Offices
    Given Multiple offices exist in the current system  
    When The user searches for available offices within a specific date range
    Then The system displays all available offices within that date range

  Scenario: No Available Offices Within Date Range
    Given Multiple offices exist in the current system
    When The user searches for available offices from "2023-07-01" to "2023-07-10"
    Then The system displays an empty list

  Scenario: Book an Office
    Given The user selects an available office
    And The user provides valid payment information with the following details:
      | cardNumber       | expiryDate | cvv | userId       |
      | 4111111111111111 | 12/24      | 123 | Test_User_Id |
    When The user submits a booking request
    Then The system creates a new booking record
    And The system triggers the "OfficeBookedEvent" domain event
    And The "OfficeBookedEvent" event contains the following attributes:
      | officeId  | bookingId                            | startDate  | endDate    | userId       |
      | 1         | 019bf648-bbec-4aca-a13b-ac04d07cb3a6 | 2023-06-01 | 2023-06-05 | Test_User_Id |

