package com.expensetracker;// com.expensetracker.ExpenseControllerTest.java

import com.expensetracker.controllers.ExpenseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseControllerTest {
    
    private ExpenseController controller;
    
    @BeforeEach
    void setUp() {
        controller = new ExpenseController();
    }
    
    @Test
    void addExpenseShouldIncreaseTotal() {
        controller.addExpense("Food", 50.0, "Lunch");
        assertEquals(50.0, controller.getTotalExpenses());
    }

    @Test
    void deleteExpenseShouldWork() {
        // Add first expense and get its ID
        controller.addExpense("Food", 50.0, "Lunch");
        int firstId = controller.getExpenses().get(0).getId();

        // Add second expense
        controller.addExpense("Transport", 30.0, "Bus");

        // Delete first expense using its actual ID
        boolean deleted = controller.deleteExpense(firstId);
        assertTrue(deleted);
        assertEquals(30.0, controller.getTotalExpenses());
    }

    @Test
    void editExpenseShouldWork() {
        // Add expense and get its ID
        controller.addExpense("Food", 50.0, "Lunch");
        int expenseId = controller.getExpenses().get(0).getId();

        // Edit expense using its actual ID
        boolean edited = controller.editExpense(expenseId, "Food", 40.0, "Updated lunch");
        assertTrue(edited);
        assertEquals(40.0, controller.getTotalExpenses());
    }

    @Test
    void getTotalExpensesShouldSumCorrectly() {
        controller.addExpense("Food", 50.0, "Lunch");
        controller.addExpense("Transport", 30.0, "Bus");
        controller.addExpense("Shopping", 120.0, "Groceries");
        
        assertEquals(200.0, controller.getTotalExpenses());
    }
    
    @Test
    void editNonExistentExpenseShouldReturnFalse() {
        boolean result = controller.editExpense(999, "Food", 40.0, "Lunch");
        assertFalse(result);
    }
    
    @Test
    void deleteNonExistentExpenseShouldReturnFalse() {
        boolean result = controller.deleteExpense(999);
        assertFalse(result);
    }
}