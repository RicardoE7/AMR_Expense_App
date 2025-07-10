package com.amr.ExpenseTracker.repository;

import com.amr.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    // Find all expenses ordered by date descending
    List<Expense> findAllByOrderByDateDesc();
    
    // Find expenses by category
    List<Expense> findByCategoryOrderByDateDesc(String category);
    
    // Find expenses by date range
    List<Expense> findByDateBetweenOrderByDateDesc(java.time.LocalDate startDate, java.time.LocalDate endDate);
    
    // Custom query to get total amount by category
    @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
    List<Object[]> getTotalByCategory();
    
    // Custom query to get total amount
    @Query("SELECT SUM(e.amount) FROM Expense e")
    java.math.BigDecimal getTotalAmount();
} 