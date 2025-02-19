package org.example;

public class Order {
    public static OrderPlaced place(OrderItems orderItems) {

        //verify if the coffee beans and milk is enough to make coffee to fulfill the orderItems
        //Invariant Constraints
        OrderSpecification.verify(orderItems);

        return new OrderPlaced();
    }
}
