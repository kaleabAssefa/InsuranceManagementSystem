package com.insurance.service;

import com.insurance.model.Policy;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles adding and viewing Policies.
 */
public class PolicyService {

    private List<Policy> policyList = new ArrayList<>();
    private int policyIdCounter = 1;

    private CategoryService categoryService;

    public PolicyService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Policy addPolicy(String policyName, int subCategoryId, double premiumAmount,
                             double coverageAmount, int durationInYears, String description) {
        // A policy must belong to a sub-category that already exists.
        if (categoryService.findSubCategoryById(subCategoryId) == null) {
            System.out.println("Cannot add policy. SubCategory ID " + subCategoryId + " does not exist.");
            return null;
        }
        Policy policy = new Policy(policyIdCounter++, policyName, subCategoryId, premiumAmount,
                coverageAmount, durationInYears, description);
        policyList.add(policy);
        return policy;
    }

    public Policy findPolicyById(int policyId) {
        for (Policy policy : policyList) {
            if (policy.getId() == policyId) {
                return policy;
            }
        }
        return null;
    }

    public List<Policy> getAllPolicies() {
        return policyList;
    }

    public List<Policy> getPoliciesBySubCategory(int subCategoryId) {
        List<Policy> result = new ArrayList<>();
        for (Policy policy : policyList) {
            if (policy.getSubCategoryId() == subCategoryId) {
                result.add(policy);
            }
        }
        return result;
    }

    public void printAllPolicies() {
        if (policyList.isEmpty()) {
            System.out.println("No policies added yet.");
            return;
        }
        for (Policy policy : policyList) {
            System.out.println(policy);
        }
    }
}
