package com.insurance.model;
public class PolicyHolder {

    private int id;
    private int customerId;
    private int policyId;
    private String purchaseDate;
    private String status; // ACTIVE, EXPIRED, CANCELLED

    public PolicyHolder(int id, int customerId, int policyId, String purchaseDate, String status) {
        this.id = id;
        this.customerId = customerId;
        this.policyId = policyId;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RecordID: " + id + " | CustomerID: " + customerId + " | PolicyID: " + policyId
                + " | PurchaseDate: " + purchaseDate + " | Status: " + status;
    }
}
