package com.insurance.model;

public class Policy {

    private int id;
    private String policyName;
    private int subCategoryId;      // which sub-category this policy belongs to
    private double premiumAmount;   // amount customer pays
    private double coverageAmount;  // amount covered by the insurance
    private int durationInYears;
    private String description;

    public Policy(int id, String policyName, int subCategoryId, double premiumAmount,
                  double coverageAmount, int durationInYears, String description) {
        this.id = id;
        this.policyName = policyName;
        this.subCategoryId = subCategoryId;
        this.premiumAmount = premiumAmount;
        this.coverageAmount = coverageAmount;
        this.durationInYears = durationInYears;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getPolicyName() {
        return policyName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public int getDurationInYears() {
        return durationInYears;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PolicyID: " + id + " | Name: " + policyName + " | SubCategoryID: " + subCategoryId
                + " | Premium: Rs." + premiumAmount + " | Coverage: Rs." + coverageAmount
                + " | Duration: " + durationInYears + " yr(s)" + " | Description: " + description;
    }
}
