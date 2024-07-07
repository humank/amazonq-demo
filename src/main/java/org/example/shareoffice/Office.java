package org.example.shareoffice;

import java.time.LocalDate;
import java.util.Objects;

public class Office {
    private int id;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    public Office(int id, String name, String location, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return id == office.id &&
                Objects.equals(name, office.name) &&
                Objects.equals(location, office.location) &&
                Objects.equals(startDate, office.startDate) &&
                Objects.equals(endDate, office.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, startDate, endDate);
    }
}
