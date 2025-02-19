package org.example;

import java.time.LocalDate;

public class Office {
    private int id;
    private LocalDate availabilityStartDate;
    private LocalDate availabilityEndDate;

    public Office(int id, LocalDate availabilityStartDate, LocalDate availabilityEndDate) {
        this.id = id;
        this.availabilityStartDate = availabilityStartDate;
        this.availabilityEndDate = availabilityEndDate;
    }

    public LocalDate getAvailabilityStartDate() {
        return availabilityStartDate;
    }

    public LocalDate getAvailabilityEndDate() {
        return availabilityEndDate;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        return (startDate.isEqual(availabilityStartDate) || startDate.isAfter(availabilityStartDate))
                && (endDate.isEqual(availabilityEndDate) || endDate.isBefore(availabilityEndDate));
    }
    
    public double calculateRentalCost(LocalDate startDate, LocalDate endDate) {
        long rentalDays = endDate.toEpochDay() - startDate.toEpochDay() + 1;
        return rentalDays * 10000; // Assuming daily rental cost is 10,000
    }
}
