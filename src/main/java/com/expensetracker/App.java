package com.expensetracker;

import com.expensetracker.controllers.ExpenseController;
import com.expensetracker.controllers.UserController;

import java.util.Scanner;

public class App {
    private static ExpenseController expenseController = new ExpenseController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (login()) {
            showMainMenu();
        } else {
            System.out.println("Exiting application. Goodbye!");
        }
    }

    private static boolean login() {
        System.out.println("=== Expense Tracker CLI ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (UserController.authenticate(username, password)) {
            System.out.println("Login Successful!\n");
            return true;
        } else {
            System.out.println("Invalid Credentials!\n");
            return false;
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Edit Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. View Reports");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addExpense();
                    break;
                case "2":
                    viewExpenses();
                    break;
                case "3":
                    editExpense();
                    break;
                case "4":
                    deleteExpense();
                    break;
                case "5":
                    viewReports();
                    break;
                case "6":
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    private static void addExpense() {
        System.out.println("\n=== Add New Expense ===");
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a numeric value.\n");
            return;
        }
        System.out.print("Description: ");
        String description = scanner.nextLine();

        expenseController.addExpense(category, amount, description);
        System.out.println("Expense added successfully!\n");
    }

    private static void viewExpenses() {
        System.out.println("\n=== View Expenses ===");
        expenseController.listExpenses();
        System.out.println();
    }

    private static void editExpense() {
        System.out.println("\n=== Edit Expense ===");
        expenseController.listExpenses();
        System.out.print("Enter the ID of the expense to edit: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a numeric value.\n");
            return;
        }

        System.out.print("New Category: ");
        String newCategory = scanner.nextLine();
        System.out.print("New Amount: ");
        double newAmount;
        try {
            newAmount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a numeric value.\n");
            return;
        }
        System.out.print("New Description: ");
        String newDescription = scanner.nextLine();

        boolean success = expenseController.editExpense(id, newCategory, newAmount, newDescription);
        if (success) {
            System.out.println("Expense updated successfully!\n");
        } else {
            System.out.println("Expense with ID " + id + " not found.\n");
        }
    }

    private static void deleteExpense() {
        System.out.println("\n=== Delete Expense ===");
        expenseController.listExpenses();
        System.out.print("Enter the ID of the expense to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a numeric value.\n");
            return;
        }

        boolean success = expenseController.deleteExpense(id);
        if (success) {
            System.out.println("Expense deleted successfully!\n");
        } else {
            System.out.println("Expense with ID " + id + " not found.\n");
        }
    }

    private static void viewReports() {
        System.out.println("\n=== Expense Reports ===");
        System.out.println("1. Total Expenses");
        System.out.println("2. Expenses by Category");
        System.out.println("3. Expenses by Date");
        System.out.print("Choose a report option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                double total = expenseController.getTotalExpenses();
                System.out.println("Total Expenses: $" + String.format("%.2f", total) + "\n");
                break;
            case "2":
                expenseController.reportByCategory();
                System.out.println();
                break;
            case "3":
                expenseController.reportByDate();
                System.out.println();
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.\n");
        }
    }
}
