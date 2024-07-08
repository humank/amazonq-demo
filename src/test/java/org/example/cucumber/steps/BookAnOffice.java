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

import static org.junit.jupiter.api.Assertions.*;


public class BookAnOffice {
    private OfficeBooking officeBooking;
    private TestContext testContext;
    private OfficeBookedEventHandler eventHandler;
    
    public BookAnOffice(OfficeBooking officeBooking, TestContext testContext, OfficeBookedEventHandler eventHandler) {
        this.officeBooking = officeBooking;
        this.testContext = testContext;
        this.eventHandler = eventHandler;
    }

    public BookAnOffice(){

        this.testContext = new TestContext();
        List<Office> offices = new ArrayList<>();
        offices.add(new Office(1, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5)));
        offices.add(new Office(2, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 15)));
        offices.add(new Office(3, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25)));
        testContext.setOffices(offices);
        testContext.setAvailableOffices(offices);
        this.officeBooking = new OfficeBooking(offices);
    }
    
    @Given("The user selects an available office")
    public void the_user_selects_an_available_office() {
        Office selectedOffice = testContext.getAvailableOffices().get(0);
        testContext.setSelectedOffice(selectedOffice);
    }
    
    @Given("The user provides valid payment information") 
    public void the_user_provides_valid_payment_information() {
        PaymentInfo paymentInfo = new PaymentInfo("1234567890123456", "12/2025", "123");
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
        
        assertEquals(testContext.getBookingId(), event.getBookingId());
        assertEquals(testContext.getSelectedOffice().getId(), event.getOfficeId());
        assertEquals(testContext.getUserId(), event.getUserId());
    }
}
