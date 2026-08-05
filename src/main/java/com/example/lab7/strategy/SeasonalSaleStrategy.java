package com.example.lab7.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice * 0.80; // ลด 20%
    }

    @Override
    public String getStrategyName() {
        return "ส่วนลดเทศกาล 20%";
    }
}