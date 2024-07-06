package org.example.cucumber.steps;

import java.util.ArrayList; 
import java.util.List;
import org.example.CoffeeOrdering;

public class TestUtils {

    public static List<String> initializeOrderedCoffees() {
        return new ArrayList<>();
    }
    
    public static void addOrderedCoffee(List<String> orderedCoffees, String coffeeType) {
        orderedCoffees.add(coffeeType);
    }
    
    public static double calculateTotalCost(List<String> orderedCoffees, boolean isToGo) {
        return CoffeeOrdering.calculateTotalCost(orderedCoffees, isToGo);
    }
}
