package org.example;

import java.util.List;

public class CoffeeOrderingSystem {

    public static final String AMERICANO = "Americano";
    public static final String LATTE = "Latte";
    
    private static final double AMERICANO_PRICE = 10.0;
    private static final double LATTE_PRICE = 12.0;
    
    public static double calculateTotalCost(List<String> orderedCoffees, boolean isToGo) {
        double totalCost = 0.0;
        for (String coffee : orderedCoffees) {
            if (coffee.equals(AMERICANO)) {
                totalCost += AMERICANO_PRICE;
            } else if (coffee.equals(LATTE)) {
                totalCost += LATTE_PRICE;
            } else {
                throw new IllegalArgumentException("Invalid coffee type: " + coffee);
            }
        }
        
        double discount = calculateDiscount(isToGo, orderedCoffees.size());
        totalCost *= (1 - discount / 100);
        
        return Math.round(totalCost * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    public static double calculateDiscount(boolean isToGo, int numberOfCoffees) {
        double discount = 0.0;
        if (isToGo) {
            discount = 10.0;
        }
        if (numberOfCoffees > 1) {
            discount += 5.0;
        }
        return discount;
    }

    public static String generateReceipt(List<String> orderedCoffees, double totalCost, boolean isToGo) {
        if (orderedCoffees.isEmpty()) {
            return "No coffee was ordered.";
        }
        
        StringBuilder receipt = new StringBuilder();
        receipt.append("==== Receipt ====\n");
        for (String coffee : orderedCoffees) {
            receipt.append(coffee).append("\n");
        }
        receipt.append("Total Cost: $").append(String.format("%.2f", totalCost)).append(" USD\n");
        receipt.append("To Go: ").append(isToGo ? "Yes" : "No").append("\n");
        receipt.append("=================");
        return receipt.toString();
    }

}
