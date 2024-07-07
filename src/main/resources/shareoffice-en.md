### Background

We are developing a shared office rental system that will allow users to book office spaces, manage rental contracts, and handle payment processes. To ensure our development process aligns closely with business needs, we have decided to use Behavior-Driven Development (BDD) along with Domain-Driven Design (DDD) principles, specifically leveraging domain events to implement business logic.

### Requirement Description

#### Use Case: Booking an Office

##### Overview
Users can search for available offices in the system and make reservations. Upon successful booking, the system will trigger a domain event to handle subsequent business logic, such as notifying the finance department to generate an invoice.

##### Details

**Scenario 1: Query Available Offices**

- **Given**: Multiple offices exist in the current system
- **When**: The user searches for available offices within a specific date range
- **Then**: The system displays all available offices within that date range

**Scenario 2: Book an Office**

- **Given**: The user selects an available office
- **And**: The user provides valid payment information
- **When**: The user submits a booking request
- **Then**: The system creates a new booking record
- **And**: The system triggers the "OfficeBooked" domain event

### Domain Event

#### Event Name: OfficeBooked

- **Description**: This event is triggered when a user successfully books an office
- **Attributes**:
    - `bookingId`: Booking ID
    - `officeId`: Office ID
    - `userId`: User ID
    - `startDate`: Rental start date
    - `endDate`: Rental end date

### Event Handling

- **Finance Department**
    - **Action**: Generate the corresponding invoice upon receiving the OfficeBooked event
    - **System**: Triggers the invoice generation service
- **Notification Service**
    - **Action**: Send a confirmation email to the user
    - **System**: Uses the email service to send the email