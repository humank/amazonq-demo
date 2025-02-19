package org.example;

import java.time.LocalDate;

public class DummySample {

   // write a method to calculate the days between 1981/01/01 until today
    public static int calculateDaysSince19810101() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Create a date object for 1981/01/01
        LocalDate startDate = LocalDate.of(1981, 1, 1);
        // Calculate the number of days between the two dates
        return startDate.until(currentDate).getDays();
    }

}


 class IntegerAddition {
    public static int addTwoIntegers(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        int sum = addTwoIntegers(a, b);
        System.out.println("The sum of " + a + " and " + b + " is: " + sum);
    }
}

