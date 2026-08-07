package com.insurance.service;

import com.insurance.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private List<Admin> adminList = new ArrayList<>();
    private int adminIdCounter = 1;

    public AdminService() {
        // Seed a default admin so the app is usable immediately.
        adminList.add(new Admin(adminIdCounter++, "Default Admin", "admin@insurance.com", "admin123"));
    }

    public Admin login(String email, String password) {
        for (Admin admin : adminList) {
            if (admin.getEmail().equalsIgnoreCase(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public Admin addAdmin(String name, String email, String password) {
        Admin admin = new Admin(adminIdCounter++, name, email, password);
        adminList.add(admin);
        return admin;
    }
}
