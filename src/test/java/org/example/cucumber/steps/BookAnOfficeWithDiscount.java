package org.example.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.BookingRecord;
import org.example.Office;
import org.example.OfficeBooking;
import org.example.PaymentInfo;
import org.example.events.OfficeBookedEvent;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;

public class BookAnOfficeWithDiscount {

    private TestContext testContext;
    private OfficeBooking officeBooking;
    private List<Office> offices;
    private BookingRecord bookingRecord;
    private OfficeBookedEvent officeBookedEvent;

    public BookAnOfficeWithDiscount(TestContext testContext, OfficeBooking officeBooking) {
        this.testContext = testContext;
        this.officeBooking = officeBooking;
        this.offices = testContext.getOffices();
    }

    @Given("The user selects an available office for the date range {string} to {string}")
    public void theUserSelectsAnAvailableOfficeForTheDateRange(String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Office> availableOffices = officeBooking.searchAvailableOffices(startDate, endDate);
        Assertions.assertTrue(!availableOffices.isEmpty(), "No available offices found for the given date range");
        testContext.setSelectedOffice(availableOffices.get(0));
    }

    @When("The user submits a booking request for the selected office")
    public void theUserSubmitsABookingRequestForTheSelectedOffice() {
        Office selectedOffice = testContext.getSelectedOffice();
        PaymentInfo paymentInfo = testContext.getPaymentInfo();
        String bookingId = officeBooking.bookOffice(selectedOffice.getId(), selectedOffice.getAvailabilityStartDate(), selectedOffice.getAvailabilityEndDate(), paymentInfo);
        bookingRecord = officeBooking.getBookingRecord(bookingId);
    }

    @Then("The system creates a new booking record with a {double} discount")
    public void theSystemCreatesANewBookingRecordWithADiscount(double expectedDiscount) {
        LocalDate tpeRegionLaunchDate = LocalDate.of(2025, 5, 20);
        long rentalDays = bookingRecord.getEndDate().toEpochDay() - bookingRecord.getStartDate().toEpochDay() + 1;
        double expectedTotalCost = 0;

        if (bookingRecord.getStartDate().isAfter(tpeRegionLaunchDate) && rentalDays > 3) {
            expectedTotalCost = offices.stream()
                    .filter(o -> o.getId() == Integer.parseInt(bookingRecord.getOfficeId()))
                    .findFirst()
                    .map(o -> o.calculateRentalCost(bookingRecord.getStartDate(), bookingRecord.getEndDate()) * expectedDiscount)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid office ID"));
        } else {
            expectedTotalCost = offices.stream()
                    .filter(o -> o.getId() == Integer.parseInt(bookingRecord.getOfficeId()))
                    .findFirst()
                    .map(o -> o.calculateRentalCost(bookingRecord.getStartDate(), bookingRecord.getEndDate()))
                    .orElseThrow(() -> new IllegalArgumentException("Invalid office ID"));
        }

        Assertions.assertEquals(expectedTotalCost, bookingRecord.getTotalCost(), 0.01);
    }

    @And("The system triggers the {string} domain event with the discounted total cost")
    public void theSystemTriggersTheDomainEventWithTheDiscountedTotalCost(String eventName) {
        officeBookedEvent = officeBooking.getOfficeBookedEventHandler().getLastEvent("OfficeBookedEvent");
        Assertions.assertEquals("OfficeBookedEvent", eventName);
        Assertions.assertEquals(bookingRecord.getTotalCost(), officeBookedEvent.getTotalCost(), 0.01);
    }
}
