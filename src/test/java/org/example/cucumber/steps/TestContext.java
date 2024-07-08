package org.example.cucumber.steps;

import org.example.Office;
import org.example.PaymentInfo;

import java.util.List;

public class TestContext {
    private List<Office> offices;
    private List<Office> availableOffices;
    private Office selectedOffice;
    private PaymentInfo paymentInfo;
    private String bookingId;
    private String userId;

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public List<Office> getAvailableOffices() {
        return availableOffices;
    }

    public void setAvailableOffices(List<Office> availableOffices) {
        this.availableOffices = availableOffices;
    }

    public Office getSelectedOffice() {
        return selectedOffice;
    }

    public void setSelectedOffice(Office selectedOffice) {
        this.selectedOffice = selectedOffice;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
