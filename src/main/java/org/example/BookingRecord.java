  
package org.example;

import java.time.LocalDate;

public class BookingRecord {
    private String bookingId;
    private String officeId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentInfo paymentInfo;
    private double totalCost;

    public BookingRecord(String bookingId, String officeId, String userId, LocalDate startDate, LocalDate endDate, PaymentInfo paymentInfo, double totalCost) {
        this.bookingId = bookingId;
        this.officeId = officeId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentInfo = paymentInfo;
        this.totalCost = totalCost;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getOfficeId() {
        return officeId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
