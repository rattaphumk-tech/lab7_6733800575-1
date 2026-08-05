package com.example.lab7.strategy;

public interface DiscountStrategy {
    double calculateFinalPrice(double originalPrice);
    String getStrategyName();
}