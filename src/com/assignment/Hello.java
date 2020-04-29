package com.assignment;

import java.util.List;

public class Hello {

    public static void main(String[] args) {
        List<String> prevOwners;

        Car car = new Car();

        car.printDetails();

        Car car2 = new Car(6, "Tim", "James", "Roger");

        car2.printDetails();
    }
}
