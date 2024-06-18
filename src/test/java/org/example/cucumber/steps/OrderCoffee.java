package org.example.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.CoffeeOrderingSystem;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class OrderCoffee {

    private boolean isToGo;
    private List<String> orderedCoffees;
    private double totalCost;
    private String receipt;
    private String errorMessage;

    @Given("the customer wants to order coffee to-go")
    public void theCustomerWantsToOrderCoffeeToGo() {
        isToGo = true;
    }

    @When("the customer orders {int} Americano")
    public void the_customer_orders_americano(int count) {
        orderedCoffees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            orderedCoffees.add(CoffeeOrderingSystem.AMERICANO);
        }
    }


    @Then("the customer should receive a {int}% discount")
    public void theCustomerShouldReceiveADiscount(int expectedDiscount) {
        double actualDiscount = CoffeeOrderingSystem.calculateDiscount(isToGo, orderedCoffees.size());
        Assertions.assertEquals(expectedDiscount, actualDiscount);
    }

    @Then("the total cost should be ${double} USD")
    public void theTotalCostShouldBeUSD(double expectedTotalCost) {
        totalCost = CoffeeOrderingSystem.calculateTotalCost(orderedCoffees, isToGo);
        Assertions.assertEquals(expectedTotalCost, totalCost);
    }

    @Given("the customer has placed an order")
    public void theCustomerHasPlacedAnOrder() {
        orderedCoffees = new ArrayList<>();
        orderedCoffees.add(CoffeeOrderingSystem.LATTE);
        orderedCoffees.add(CoffeeOrderingSystem.AMERICANO);
        isToGo = true;
    }

    @When("the order is successfully processed")
    public void theOrderIsSuccessfullyProcessed() {
        totalCost = CoffeeOrderingSystem.calculateTotalCost(orderedCoffees, isToGo);
        receipt = CoffeeOrderingSystem.generateReceipt(orderedCoffees, totalCost, isToGo);
    }

    @Then("the customer should receive a receipt")
    public void theCustomerShouldReceiveAReceipt() {
        Assertions.assertNotNull(receipt);
        Assertions.assertTrue(receipt.contains("Receipt"));
        Assertions.assertTrue(receipt.contains(CoffeeOrderingSystem.LATTE));
        Assertions.assertTrue(receipt.contains(CoffeeOrderingSystem.AMERICANO));
        Assertions.assertTrue(receipt.contains(String.format("%.2f", totalCost)));
    }

    @When("the customer does not order any coffee")
    public void theCustomerDoesNotOrderAnyCoffee() {
        orderedCoffees = new ArrayList<>();
    }

    @Given("the customer wants to dine-in")
    public void theCustomerWantsToDineIn() {
        isToGo = false;
    }
    @When("the customer orders a latte")
    public void the_customer_orders_a_latte() {
        orderedCoffees = new ArrayList<>();
        orderedCoffees.add(CoffeeOrderingSystem.LATTE);
    }


    @When("the customer orders an {string}")
    public void theCustomerOrdersAn(String coffeeType) {
        orderedCoffees = new ArrayList<>();
        orderedCoffees.add(coffeeType);
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        try {
            CoffeeOrderingSystem.calculateTotalCost(orderedCoffees, isToGo);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertNotNull(errorMessage);
        Assertions.assertTrue(errorMessage.contains("Invalid coffee type"));
    }
}
