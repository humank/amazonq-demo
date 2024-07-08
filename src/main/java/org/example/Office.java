package org.example;

import java.time.LocalDate;

public class Office {
    private int id;

    public LocalDate getAvailabilityStartDate() {
        return availabilityStartDate;
    }

    public void setAvailabilityStartDate(LocalDate availabilityStartDate) {
        this.availabilityStartDate = availabilityStartDate;
    }

    public LocalDate getAvailabilityEndDate() {
        return availabilityEndDate;
    }

    public void setAvailabilityEndDate(LocalDate availabilityEndDate) {
        this.availabilityEndDate = availabilityEndDate;
    }

    private LocalDate availabilityStartDate;
    private LocalDate availabilityEndDate;

    public Office(int id, LocalDate availabilityStartDate, LocalDate availabilityEndDate) {
        this.id = id;
        this.availabilityStartDate = availabilityStartDate;
        this.availabilityEndDate = availabilityEndDate;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        return (startDate.isEqual(availabilityStartDate) || startDate.isAfter(availabilityStartDate))
                && (endDate.isEqual(availabilityEndDate) || endDate.isBefore(availabilityEndDate));
    }
}
