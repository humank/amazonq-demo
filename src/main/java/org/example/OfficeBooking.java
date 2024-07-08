package org.example;
import org.example.events.OfficeBookedEvent;
import org.example.events.OfficeBookedEventHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class OfficeBooking {

    private List<Office> offices;
    private Map<String, BookingRecord> bookingRecords;
    private OfficeBookedEventHandler eventHandler;

    public OfficeBooking(List<Office> offices) {
        this.offices = offices;
        this.bookingRecords = new HashMap<>();
        this.eventHandler = new OfficeBookedEventHandler();
    }

    public List<Office> searchAvailableOffices(LocalDate startDate, LocalDate endDate) {
        return offices.stream()
                .filter(office -> office.isAvailable(startDate, endDate))
                .collect(Collectors.toList());
    }
    
    public String bookOffice(int officeId, LocalDate startDate, LocalDate endDate, PaymentInfo paymentInfo) {
        Office office = offices.stream()
                .filter(o -> o.getId() == officeId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid office ID"));
        
        if (!office.isAvailable(startDate, endDate)) {
            throw new IllegalStateException("Office is not available for the given dates");
        }
        
        String bookingId = UUID.randomUUID().toString();
        BookingRecord bookingRecord = new BookingRecord(bookingId, String.valueOf(officeId), "userId", startDate, endDate, paymentInfo);
        bookingRecords.put(bookingId, bookingRecord);
        
        OfficeBookedEvent event = new OfficeBookedEvent(officeId, bookingId, startDate, endDate);
        eventHandler.handleEvent(event);
        
        return bookingId;
    }
    
    public LocalDate getAvailableFrom(int officeId) {
        return offices.stream()
                .filter(o -> o.getId() == officeId)
                .findFirst()
                .map(Office::getAvailabilityStartDate)
                .orElseThrow(() -> new IllegalArgumentException("Invalid office ID"));
    }
    
    public LocalDate getAvailableTo(int officeId) {
        return offices.stream()
                .filter(o -> o.getId() == officeId)
                .findFirst()
                .map(Office::getAvailabilityEndDate)
                .orElseThrow(() -> new IllegalArgumentException("Invalid office ID"));
    }
    
    public BookingRecord getBookingRecord(String bookingId) {
        return bookingRecords.get(bookingId);
    }

    public OfficeBookedEventHandler getOfficeBookedEventHandler() {
        return eventHandler;
    }
}
