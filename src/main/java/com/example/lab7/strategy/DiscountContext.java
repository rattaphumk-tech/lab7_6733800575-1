package com.example.lab7.strategy;

public class DiscountContext {
    private DiscountStrategy strategy;

    public DiscountContext(String discountType) {
        if (discountType == null) {
            this.strategy = new NoDiscountStrategy();
            return;
        }

        switch (discountType.toUpperCase()) {
            case "STUDENT":
                this.strategy = new StudentDiscountStrategy();
                break;
            case "SEASONAL":
                this.strategy = new SeasonalSaleStrategy();
                break;
            case "NONE":
            default:
                this.strategy = new NoDiscountStrategy();
                break;
        }
    }

    public double executeStrategy(double price) {
        return strategy.calculateFinalPrice(price);
    }

    public String getStrategyName() {
        return strategy.getStrategyName();
    }
}