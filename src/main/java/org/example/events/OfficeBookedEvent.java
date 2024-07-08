package org.example.events;

import java.time.LocalDate;

public class OfficeBookedEvent {
    private int officeId;
    private String bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userId;

    public OfficeBookedEvent(int officeId, String bookingId, LocalDate startDate, LocalDate endDate) {
        this.officeId = officeId;
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getOfficeId() {
        return officeId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getUserId() {
        return userId;
    }
}
