package org.example.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Office;
import org.example.OfficeBooking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchForAvailableOffices {

    private TestContext testContext;
    List<Office> availableOffices;

    public SearchForAvailableOffices() {
    }

    public SearchForAvailableOffices(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Multiple offices exist in the current system")
    public void multiple_offices_exist_in_the_current_system() {
        List<Office> offices = new ArrayList<>();
        testContext = new TestContext();
        offices.add(new Office(1, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5)));
        offices.add(new Office(2, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 15)));
        offices.add(new Office(3, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25)));
        testContext.setOffices(offices);
    }

    @When("The user searches for available offices within a specific date range")
    public void the_user_searches_for_available_offices_within_a_specific_date_range() {
        LocalDate startDate = LocalDate.of(2023, 6, 10);
        LocalDate endDate = LocalDate.of(2023, 6, 13);
        OfficeBooking officeBooking = new OfficeBooking(testContext.getOffices());
        availableOffices = officeBooking.searchAvailableOffices(startDate, endDate);
    }

    @Then("The system displays all available offices within that date range")
    public void the_system_displays_all_available_offices_within_that_date_range() {
        assertThat(availableOffices, hasSize(1));
    }
}
