package org.example.cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Office;
import org.example.OfficeBooking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoAvailableOfficesWithinDateRange {

    private TestContext testContext;
    private OfficeBooking officeBooking;

    public NoAvailableOfficesWithinDateRange() {

        this.testContext = new TestContext();
        List<Office> offices = new ArrayList<>();
        offices.add(new Office(1, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5)));
        offices.add(new Office(2, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 15)));
        offices.add(new Office(3, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25)));
        testContext.setOffices(offices);
        this.officeBooking = new OfficeBooking(offices);
    }


    public NoAvailableOfficesWithinDateRange(TestContext testContext) {
        this.testContext = testContext;
        this.officeBooking = new OfficeBooking(testContext.getOffices());
    }

    @When("The user searches for available offices from {string} to {string}")
    public void the_user_searches_for_available_offices_from_to(String startDate, String endDate) {
        testContext.setAvailableOffices(officeBooking.searchAvailableOffices(LocalDate.parse(startDate), LocalDate.parse(endDate)));
    }

    @Then("The system displays an empty list")
    public void the_system_displays_an_empty_list() {
        assertTrue(testContext.getAvailableOffices().isEmpty());
    }
    
}
