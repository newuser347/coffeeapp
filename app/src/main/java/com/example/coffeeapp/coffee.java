package com.example.coffeeapp;

public class coffee {
    private String coffeeName;
    private double coffeePrice;

    public coffee(String coffeeName, double coffeePrice) {
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public double getCoffeePrice() {
        return coffeePrice;
    }
}
