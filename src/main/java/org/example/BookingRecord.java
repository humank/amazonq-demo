  
package org.example;

import java.time.LocalDate;

public class BookingRecord {
    private final PaymentInfo paymentInfo;
    private String bookingId;
    private String officeId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookingRecord(String bookingId, String officeId, String userId, LocalDate startDate, LocalDate endDate, PaymentInfo paymentInfo) {
        this.bookingId = bookingId;
        this.officeId = officeId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentInfo = paymentInfo;
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
}
