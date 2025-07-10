package com.amr.ExpenseTracker.controller;

import com.amr.ExpenseTracker.model.Expense;
import com.amr.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    // Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
    
    // Get expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        try {
            Expense expense = expenseService.getExpenseById(id);
            return ResponseEntity.ok(expense);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Create new expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        try {
            Expense savedExpense = expenseService.saveExpense(expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Update expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        try {
            Expense updatedExpense = expenseService.updateExpense(id, expenseDetails);
            return ResponseEntity.ok(updatedExpense);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get expenses by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category) {
        List<Expense> expenses = expenseService.getExpensesByCategory(category);
        return ResponseEntity.ok(expenses);
    }
    
    // Get expenses by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Expense>> getExpensesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Expense> expenses = expenseService.getExpensesByDateRange(startDate, endDate);
        return ResponseEntity.ok(expenses);
    }
    
    // Get total amount
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalAmount() {
        BigDecimal total = expenseService.getTotalAmount();
        return ResponseEntity.ok(total);
    }
    
    // Get total by category
    @GetMapping("/total-by-category")
    public ResponseEntity<Map<String, BigDecimal>> getTotalByCategory() {
        Map<String, BigDecimal> categoryTotals = expenseService.getTotalByCategory();
        return ResponseEntity.ok(categoryTotals);
    }
    
    // Get expense summary
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getExpenseSummary() {
        Map<String, Object> summary = expenseService.getExpenseSummary();
        return ResponseEntity.ok(summary);
    }
    
    // Debug endpoint to check for problematic expenses
    @GetMapping("/debug")
    public ResponseEntity<String> debugExpenses() {
        List<Expense> allExpenses = expenseService.getAllExpenses();
        StringBuilder debug = new StringBuilder();
        debug.append("Total expenses: ").append(allExpenses.size()).append("\n\n");
        
        for (int i = 0; i < allExpenses.size(); i++) {
            Expense expense = allExpenses.get(i);
            debug.append("Row ").append(i + 1).append(":\n");
            debug.append("  ID: ").append(expense.getId()).append("\n");
            debug.append("  Date: ").append(expense.getDate()).append("\n");
            debug.append("  Category: ").append(expense.getCategory()).append("\n");
            debug.append("  Amount: ").append(expense.getAmount()).append("\n");
            debug.append("  Description: ").append(expense.getDescription()).append("\n");
            
            // Check for null/empty values
            if (expense.getDate() == null || expense.getCategory() == null || 
                expense.getAmount() == null || expense.getDescription() == null ||
                expense.getDescription().trim().isEmpty()) {
                debug.append("  *** PROBLEMATIC EXPENSE - HAS NULL/EMPTY VALUES ***\n");
            }
            debug.append("\n");
        }
        
        return ResponseEntity.ok(debug.toString());
    }
} 