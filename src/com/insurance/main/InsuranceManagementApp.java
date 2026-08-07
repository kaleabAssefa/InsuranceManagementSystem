package com.insurance.main;

import com.insurance.model.Admin;
import com.insurance.model.Customer;
import com.insurance.service.AdminService;
import com.insurance.service.CategoryService;
import com.insurance.service.CustomerService;
import com.insurance.service.PolicyService;

import java.util.Scanner;

/**
 * Insurance Management System
 * -----------------------------------
 * A simple, beginner level, console based Java application
 * built with packages (model / service / main) instead of a database,
 * so it can be compiled and run directly with javac/java.
 *
 * Two roles are supported:
 *   1) Admin (policymaker) - adds Category, SubCategory and Policy.
 *   2) Customer - registers, buys a policy, and views policies held.
 */
public class InsuranceManagementApp {

    private static Scanner scanner = new Scanner(System.in);

    // Services hold all the in-memory data and business logic.
    private static CategoryService categoryService = new CategoryService();
    private static PolicyService policyService = new PolicyService(categoryService);
    private static CustomerService customerService = new CustomerService(policyService);
    private static AdminService adminService = new AdminService();

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("      WELCOME TO THE INSURANCE MANAGEMENT SYSTEM     ");
        System.out.println("=====================================================");

        boolean exitApp = false;
        while (!exitApp) {
            System.out.println("\nSelect Login Type:");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    customerRegistration();
                    break;
                case 4:
                    exitApp = true;
                    System.out.println("Thank you for using the Insurance Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ---------------------------------------------------------------
    // ADMIN FLOW
    // ---------------------------------------------------------------

    private static void adminLogin() {
        System.out.print("Enter admin email: ");
        String email = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        Admin admin = adminService.login(email, password);
        if (admin == null) {
            System.out.println("Invalid credentials. (Hint: default admin is admin@insurance.com / admin123)");
            return;
        }

        System.out.println("Login successful. Welcome, " + admin.getName() + "!");
        adminMenu();
    }

    private static void adminMenu() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n--------------- ADMIN MENU ---------------");
            System.out.println("1. Add Category");
            System.out.println("2. Add SubCategory");
            System.out.println("3. Add Policy");
            System.out.println("4. View All Categories");
            System.out.println("5. View All SubCategories");
            System.out.println("6. View All Policies");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    addSubCategory();
                    break;
                case 3:
                    addPolicy();
                    break;
                case 4:
                    categoryService.printAllCategories();
                    break;
                case 5:
                    categoryService.printAllSubCategories();
                    break;
                case 6:
                    policyService.printAllPolicies();
                    break;
                case 7:
                    logout = true;
                    System.out.println("Logged out from Admin.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category description: ");
        String description = scanner.nextLine();

        categoryService.addCategory(name, description);
        System.out.println("Category added successfully.");
    }

    private static void addSubCategory() {
        categoryService.printAllCategories();
        System.out.print("Enter category ID this sub-category belongs to: ");
        int categoryId = readInt();

        System.out.print("Enter sub-category name: ");
        String name = scanner.nextLine();
        System.out.print("Enter sub-category description: ");
        String description = scanner.nextLine();

        if (categoryService.addSubCategory(name, description, categoryId) != null) {
            System.out.println("SubCategory added successfully.");
        }
    }

    private static void addPolicy() {
        categoryService.printAllSubCategories();
        System.out.print("Enter sub-category ID this policy belongs to: ");
        int subCategoryId = readInt();

        System.out.print("Enter policy name: ");
        String name = scanner.nextLine();
        System.out.print("Enter premium amount: ");
        double premium = readDouble();
        System.out.print("Enter coverage amount: ");
        double coverage = readDouble();
        System.out.print("Enter duration (in years): ");
        int duration = readInt();
        System.out.print("Enter policy description: ");
        String description = scanner.nextLine();

        if (policyService.addPolicy(name, subCategoryId, premium, coverage, duration, description) != null) {
            System.out.println("Policy added successfully.");
        }
    }

    // ---------------------------------------------------------------
    // CUSTOMER FLOW
    // ---------------------------------------------------------------

    private static void customerRegistration() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        Customer customer = customerService.register(name, email, password, phone);
        if (customer != null) {
            System.out.println("Registration successful! You can now login.");
        }
    }

    private static void customerLogin() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer = customerService.login(email, password);
        if (customer == null) {
            System.out.println("Invalid credentials or account not found. Please register first.");
            return;
        }

        System.out.println("Login successful. Welcome, " + customer.getName() + "!");
        customerMenu(customer);
    }

    private static void customerMenu(Customer customer) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n--------------- CUSTOMER MENU ---------------");
            System.out.println("1. View Available Policies");
            System.out.println("2. Buy a Policy");
            System.out.println("3. View Policies I Hold");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            switch (choice) {
                case 1:
                    policyService.printAllPolicies();
                    break;
                case 2:
                    buyPolicy(customer);
                    break;
                case 3:
                    customerService.printPoliciesHeldByCustomer(customer.getId());
                    break;
                case 4:
                    logout = true;
                    System.out.println("Logged out from Customer.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void buyPolicy(Customer customer) {
        policyService.printAllPolicies();
        System.out.print("Enter the Policy ID you want to buy: ");
        int policyId = readInt();

        if (customerService.buyPolicy(customer.getId(), policyId) != null) {
            System.out.println("Policy purchased successfully!");
        }
    }

    // ---------------------------------------------------------------
    // SMALL HELPERS - to safely read numbers from the console
    // ---------------------------------------------------------------

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
