package org.example.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.shareoffice.Office;
import org.example.shareoffice.OfficeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchAvailableOfficeTest {
    private List<Office> officeList;
    private OfficeService officeService;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Office> searchResults;

    @Given("Multiple offices exist in the current system")
    public void multiple_offices_exist_in_the_current_system() {
        officeList = new ArrayList<>();
        //officeList.add(new Office(1, "Office A", "Location A", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15)));
        officeList.add(new Office(2, "Office B", "Location B", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 25)));
        officeList.add(new Office(3, "Office C", "Location C", LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 30)));
        officeService = new OfficeService(officeList);
    }

    @When("The user searches for available offices within a specific date range")
    public void the_user_searches_for_available_offices_within_a_specific_date_range() {
        startDate = LocalDate.of(2023, 6, 5);
        endDate = LocalDate.of(2023, 6, 30);
        searchResults = officeService.getAvailableOffices(startDate, endDate);
    }

    @Then("The system displays all available offices within that date range")
    public void the_system_displays_all_available_offices_within_that_date_range() {
        List<Office> expectedOffices = new ArrayList<>();
        expectedOffices.add(new Office(1, "Office A", "Location A", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 25)));
        expectedOffices.add(new Office(2, "Office B", "Location B", LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 30)));
        assertEquals(expectedOffices.size(), searchResults.size());
    }
}
