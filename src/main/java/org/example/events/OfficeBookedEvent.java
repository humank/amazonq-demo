package org.example.events;

import java.time.LocalDate;

public class OfficeBookedEvent {
    private int officeId;
    private String bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userId;
    private double totalCost;

    public OfficeBookedEvent(int officeId, String bookingId, LocalDate startDate, LocalDate endDate, String userId, double totalCost) {
        this.officeId = officeId;
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.totalCost = totalCost;
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

    public double getTotalCost() {
        return totalCost;
    }
}
