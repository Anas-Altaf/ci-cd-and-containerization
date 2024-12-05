package com.expensetracker.controllers;

public class UserController {
    // Predefined users (username:password)
    private static final String[][] users = {
            {"admin", "password"},
            {"user1", "pass123"},
            {"user2", "mypassword"}
    };

    // Authenticate user credentials
    public static boolean authenticate(String username, String password) {
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}
