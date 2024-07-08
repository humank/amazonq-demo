package org.example.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.BookingRecord;
import org.example.Office;
import org.example.OfficeBooking;
import org.example.PaymentInfo;
import org.example.events.OfficeBookedEvent;
import org.example.events.OfficeBookedEventHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class BookAnOffice {
    private OfficeBooking officeBooking;
    private TestContext testContext;
    private OfficeBookedEventHandler eventHandler;

    public BookAnOffice() {

        this.testContext = new TestContext();
        List<Office> offices = new ArrayList<>();
        offices.add(new Office(1, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5)));
        offices.add(new Office(2, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 15)));
        offices.add(new Office(3, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25)));
        testContext.setOffices(offices);
        testContext.setAvailableOffices(offices);
        testContext.setUserId("Test_User_Id");
        this.officeBooking = new OfficeBooking(offices);
    }

    @Given("The user selects an available office")
    public void the_user_selects_an_available_office() {
        Office selectedOffice = testContext.getAvailableOffices().get(0);
        testContext.setSelectedOffice(selectedOffice);
    }

    @Given("The user provides valid payment information with the following details:")
    public void the_user_provides_valid_payment_information_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        Map<String, String> paymentInfoMap = data.get(0);
        PaymentInfo paymentInfo = new PaymentInfo(
                paymentInfoMap.get("cardNumber"),
                paymentInfoMap.get("expiryDate"),
                paymentInfoMap.get("cvv"),
                paymentInfoMap.get("userId")
        );
        testContext.setPaymentInfo(paymentInfo);
    }

    @When("The user submits a booking request")
    public void the_user_submits_a_booking_request() {
        Office selectedOffice = testContext.getSelectedOffice();
        PaymentInfo paymentInfo = testContext.getPaymentInfo();
        String bookingId = officeBooking.bookOffice(selectedOffice.getId(), selectedOffice.getAvailabilityStartDate(), selectedOffice.getAvailabilityEndDate(), paymentInfo);
        testContext.setBookingId(bookingId);
        eventHandler = officeBooking.getOfficeBookedEventHandler();
    }

    @Then("The system creates a new booking record")
    public void the_system_creates_a_new_booking_record() {
        String bookingId = testContext.getBookingId();
        BookingRecord bookingRecord = officeBooking.getBookingRecord(bookingId);
        assertNotNull(bookingRecord);
        assertEquals(bookingId, bookingRecord.getBookingId());
    }

    @Then("The system triggers the {string} domain event")
    public void the_system_triggers_the_domain_event(String eventName) {
        assertTrue(eventHandler.isEventTriggered(eventName));
    }

    @Then("The {string} event contains the following attributes:")
    public void the_event_contains_the_following_attributes(String eventName, io.cucumber.datatable.DataTable dataTable) {

        OfficeBookedEvent event = (OfficeBookedEvent) eventHandler.getLastEvent(eventName);
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        Map<String, String> expectedAttributes = data.get(0);

        assertTrue(isValidUUID(expectedAttributes.get("bookingId")));
        assertEquals( Integer.parseInt(expectedAttributes.get("officeId")), event.getOfficeId());
        assertEquals(transformDate(expectedAttributes.get("startDate")), event.getStartDate());
        assertEquals(transformDate(expectedAttributes.get("endDate")), event.getEndDate());
        assertEquals(expectedAttributes.get("userId"), event.getUserId());
    }

    // validate the data string see if it is an UUID format
    private boolean isValidUUID(String str) {
        // Regular expression pattern for UUID format
        String pattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        return str.matches(pattern);
    }

    // transform the date string into a LocalDate object
    private LocalDate transformDate(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return LocalDate.of(year, month, day);
    }
}
