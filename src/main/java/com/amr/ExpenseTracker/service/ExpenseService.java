package com.amr.ExpenseTracker.service;

import com.amr.ExpenseTracker.model.Expense;
import com.amr.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAllByOrderByDateDesc();
    }
    
    // Get expense by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }
    
    // Save new expense
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    // Update existing expense
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = getExpenseById(id);
        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());
        expense.setCategory(expenseDetails.getCategory());
        return expenseRepository.save(expense);
    }
    
    // Delete expense
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }
    
    // Get expenses by category
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategoryOrderByDateDesc(category);
    }
    
    // Get expenses by date range
    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetweenOrderByDateDesc(startDate, endDate);
    }
    
    // Get total amount
    public BigDecimal getTotalAmount() {
        BigDecimal total = expenseRepository.getTotalAmount();
        return total != null ? total : BigDecimal.ZERO;
    }
    
    // Get total amount by category
    public Map<String, BigDecimal> getTotalByCategory() {
        List<Object[]> results = expenseRepository.getTotalByCategory();
        Map<String, BigDecimal> categoryTotals = new HashMap<>();
        
        for (Object[] result : results) {
            String category = (String) result[0];
            BigDecimal amount = (BigDecimal) result[1];
            categoryTotals.put(category, amount);
        }
        
        return categoryTotals;
    }
    
    // Get expense summary
    public Map<String, Object> getExpenseSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalAmount", getTotalAmount());
        summary.put("categoryTotals", getTotalByCategory());
        summary.put("totalExpenses", expenseRepository.count());
        return summary;
    }
} 