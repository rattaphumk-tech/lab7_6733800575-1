package com.example.lab7.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getStrategyName() {
        return "ราคาปกติ";
    }
}