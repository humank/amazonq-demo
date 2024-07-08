package org.example.shareoffice;

import java.time.LocalDate;

public class OfficeBooked {
    private final Object office;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public OfficeBooked(Office office, LocalDate startDate, LocalDate endDate) {

        this.office = office;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
