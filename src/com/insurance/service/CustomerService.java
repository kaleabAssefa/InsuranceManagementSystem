package com.insurance.service;

import com.insurance.model.Customer;
import com.insurance.model.Policy;
import com.insurance.model.PolicyHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {

    private List<Customer> customerList = new ArrayList<>();
    private List<PolicyHolder> policyHolderList = new ArrayList<>();

    private int customerIdCounter = 1;
    private int policyHolderIdCounter = 1;

    private PolicyService policyService;

    public CustomerService(PolicyService policyService) {
        this.policyService = policyService;
    }

    public Customer register(String name, String email, String password, String phoneNumber) {
        if (findCustomerByEmail(email) != null) {
            System.out.println("A customer with this email already exists. Please login instead.");
            return null;
        }
        Customer customer = new Customer(customerIdCounter++, name, email, password, phoneNumber);
        customerList.add(customer);
        return customer;
    }

    public Customer login(String email, String password) {
        Customer customer = findCustomerByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    public Customer findCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null;
    }

    public PolicyHolder buyPolicy(int customerId, int policyId) {
        Policy policy = policyService.findPolicyById(policyId);
        if (policy == null) {
            System.out.println("Policy ID " + policyId + " does not exist.");
            return null;
        }
        String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        PolicyHolder policyHolder = new PolicyHolder(policyHolderIdCounter++, customerId, policyId, today, "ACTIVE");
        policyHolderList.add(policyHolder);
        return policyHolder;
    }

    // Returns the list of policies that a particular customer holds.
    public List<PolicyHolder> getPoliciesHeldByCustomer(int customerId) {
        List<PolicyHolder> result = new ArrayList<>();
        for (PolicyHolder policyHolder : policyHolderList) {
            if (policyHolder.getCustomerId() == customerId) {
                result.add(policyHolder);
            }
        }
        return result;
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public void printPoliciesHeldByCustomer(int customerId) {
        List<PolicyHolder> holdings = getPoliciesHeldByCustomer(customerId);
        if (holdings.isEmpty()) {
            System.out.println("You do not hold any policies yet.");
            return;
        }
        for (PolicyHolder holder : holdings) {
            Policy policy = policyService.findPolicyById(holder.getPolicyId());
            System.out.println("-------------------------------------------------");
            System.out.println(holder);
            if (policy != null) {
                System.out.println(policy);
            }
        }
        System.out.println("-------------------------------------------------");
    }
}
