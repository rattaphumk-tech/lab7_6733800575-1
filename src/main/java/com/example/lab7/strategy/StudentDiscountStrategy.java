package com.example.lab7.strategy;

public class StudentDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice * 0.90; // ลด 10%
    }

    @Override
    public String getStrategyName() {
        return "ส่วนลดนักศึกษา 10%";
    }
}