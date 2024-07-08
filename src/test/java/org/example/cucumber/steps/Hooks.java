package org.example.cucumber.steps;

import io.cucumber.java.Before;

public class Hooks {

    private TestContext testContext;

    @Before
    public void setUp() {
        testContext = new TestContext();
        new SearchForAvailableOffices(testContext);
        new NoAvailableOfficesWithinDateRange(testContext);
    }
}
