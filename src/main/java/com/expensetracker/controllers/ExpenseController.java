package com.expensetracker.controllers;

import com.expensetracker.models.Expense;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseController {
    private ArrayList<Expense> expenses;

    public ExpenseController() {
        expenses = new ArrayList<>();
    }

    // Add a new expense
    public void addExpense(String category, double amount, String description) {
        Expense expense = new Expense(category, amount, description);
        expenses.add(expense);
    }

    // List all expenses
    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\n--- Expenses ---");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    // Edit an existing expense
    public boolean editExpense(int id, String newCategory, double newAmount, String newDescription) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expense.setCategory(newCategory);
                expense.setAmount(newAmount);
                expense.setDescription(newDescription);
                return true;
            }
        }
        return false;
    }

    // Delete an expense
    public boolean deleteExpense(int id) {
        return expenses.removeIf(expense -> expense.getId() == id);
    }

    // Get total expenses
    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    // Report expenses by category
    public void reportByCategory() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to report.");
            return;
        }

        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense expense : expenses) {
            categoryTotals.put(expense.getCategory(),
                    categoryTotals.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }

        System.out.println("\n--- Expenses by Category ---");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.println(entry.getKey() + ": $" + String.format("%.2f", entry.getValue()));
        }
    }
    // Add to ExpenseController class
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
    // Report expenses by date (Assuming description contains date for simplicity)
    // Since we don't have a date field, we'll skip this or implement a simple version
    public void reportByDate() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to report.");
            return;
        }

        Map<String, Double> dateTotals = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Expense expense : expenses) {
            String dateStr = expense.getDate().format(formatter);
            dateTotals.put(dateStr,
                    dateTotals.getOrDefault(dateStr, 0.0) + expense.getAmount());
        }

        System.out.println("\n--- Expenses by Date ---");
        for (Map.Entry<String, Double> entry : dateTotals.entrySet()) {
            System.out.println(entry.getKey() + ": $" + String.format("%.2f", entry.getValue()));
        }
    }
}
