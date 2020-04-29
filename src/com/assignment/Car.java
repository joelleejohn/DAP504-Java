package com.assignment;

public class Car {
    Car(){
        this.setColor("Black");
        this.setWheels(4);
    }

    Car(int wheels){
        this.setColor("Black");
        this.setWheels(wheels);
    }

    Car(String color){
        this.setColor(color);
        this.setWheels(4);
    }

    Car(String color, int wheels){
        this.setColor(color);
        this.setWheels(wheels);
    }

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
    }

}
