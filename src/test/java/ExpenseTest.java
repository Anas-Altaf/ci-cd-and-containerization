// ExpenseTest.java

import com.expensetracker.models.Expense;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ExpenseTest {

    @Test
    void constructorShouldInitializeCorrectly() {
        Expense expense = new Expense("Food", 50.0, "Lunch");
        
        assertNotNull(expense);
        assertEquals("Food", expense.getCategory());
        assertEquals(50.0, expense.getAmount());
        assertEquals("Lunch", expense.getDescription());
        assertEquals(LocalDate.now(), expense.getDate());
    }

    @Test
    void idsShouldBeUnique() {
        Expense expense1 = new Expense("Food", 50.0, "Lunch");
        Expense expense2 = new Expense("Transport", 30.0, "Bus");
        
        assertNotEquals(expense1.getId(), expense2.getId());
    }

    @Test
    void settersShouldUpdateValues() {
        Expense expense = new Expense("Food", 50.0, "Lunch");
        LocalDate newDate = LocalDate.of(2024, 1, 1);
        
        expense.setCategory("Transport");
        expense.setAmount(30.0);
        expense.setDescription("Bus fare");
        expense.setDate(newDate);
        
        assertEquals("Transport", expense.getCategory());
        assertEquals(30.0, expense.getAmount());
        assertEquals("Bus fare", expense.getDescription());
        assertEquals(newDate, expense.getDate());
    }
}

