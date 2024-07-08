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
    And The user provides valid payment information
    When The user submits a booking request
    Then The system creates a new booking record
    And The system triggers the "OfficeBookedEvent" domain event
    And The "OfficeBookedEvent" event contains the following attributes:
      | Attribute  | Value                  |
      | bookingId  | [generated booking ID] |
      | officeId   | [selected office ID]   |
      | userId     | [user ID]              |
