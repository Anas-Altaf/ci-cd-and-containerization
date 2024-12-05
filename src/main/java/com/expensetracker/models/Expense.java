package com.expensetracker.models;

import java.time.LocalDate;

public class Expense {
    private static int counter = 1; // To assign unique IDs
    private int id;
    private String category;
    private double amount;
    private String description;
    private LocalDate date;

    public Expense(String category, double amount, String description) {
        this.id = counter++;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
    }

    // ID getter (no setter as it's auto-generated)
    public int getId() {
        return id;
    }

    // Category getter and setter
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Amount getter and setter
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Description getter and setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Existing date getter and setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Date: %s | Category: %s | Amount: $%.2f | Description: %s",
                id, date, category, amount, description);
    }
}