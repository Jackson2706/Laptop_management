package com.example.laptop_management;

public class BrandData {
    private String brandID;
    private String brandName;
    private String brandStarRate;

    public BrandData(String brandID, String brandName, String brandStarRate) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandStarRate = brandStarRate;
    }

    public String getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBrandStarRate() {
        return brandStarRate;
    }

    @Override
    public String toString() {
        return this.brandName + " - " + this.brandStarRate + " star(s)";
    }
}
