package com.andile.Flyaway;

import java.util.HashMap;
import java.util.Map;

public class AdminService {
    private Map<String, String> adminCredentials;

    public AdminService() {
        adminCredentials = new HashMap<>();
        // Hardcoded admin credentials for demonstration purposes
        adminCredentials.put("admin", "admin@Flyaway");
    }

    public boolean authenticateAdmin(String username, String password) {
        String storedPassword = adminCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}
