package com.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Car {
    Car(){
        this.setColor("Black");
        this.setWheels(4);
        this.previousOwners.add("Roger");
    }

    Car(int wheels, String... previousOwners){
        this.setColor("Black");
        this.setWheels(wheels);

        for (String prevOwner: previousOwners){
            this.previousOwners.add(prevOwner);
        }
    }

    Car(String color){
        this.setColor(color);
        this.setWheels(4);
    }

    Car(String color, int wheels){
        this.setColor(color);
        this.setWheels(wheels);
    }

    private List<String> previousOwners = new ArrayList<String>();
    
    private String color;

    private int wheels;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public void printDetails(){
        System.out.println("Car wheels: " + this.wheels);
        System.out.println("Car colour: " + this.color);

        for (int position = 0;  position < this.previousOwners.size(); position++) {
            System.out.println("Owner number " + position + " is " + this.previousOwners.get(position) );
        }
    }

}
