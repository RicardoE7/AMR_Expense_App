package com.amr.ExpenseTracker.config;

import com.amr.ExpenseTracker.model.Expense;
import com.amr.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Only add sample data if the database is empty
        if (expenseRepository.count() == 0) {
            addSampleData();
        }
    }
    
    private void addSampleData() {
        // Sample expenses for the current month
        LocalDate today = LocalDate.now();
        
        Expense expense1 = new Expense();
        expense1.setDescription("Lunch at restaurant");
        expense1.setAmount(new BigDecimal("25.50"));
        expense1.setDate(today.minusDays(1));
        expense1.setCategory("Food");
        expenseRepository.save(expense1);
        
        Expense expense2 = new Expense();
        expense2.setDescription("Gas station fill-up");
        expense2.setAmount(new BigDecimal("45.00"));
        expense2.setDate(today.minusDays(2));
        expense2.setCategory("Transportation");
        expenseRepository.save(expense2);
        
        Expense expense3 = new Expense();
        expense3.setDescription("Movie tickets");
        expense3.setAmount(new BigDecimal("32.00"));
        expense3.setDate(today.minusDays(3));
        expense3.setCategory("Entertainment");
        expenseRepository.save(expense3);
        
        Expense expense4 = new Expense();
        expense4.setDescription("Grocery shopping");
        expense4.setAmount(new BigDecimal("85.75"));
        expense4.setDate(today.minusDays(4));
        expense4.setCategory("Food");
        expenseRepository.save(expense4);
        
        Expense expense5 = new Expense();
        expense5.setDescription("Electricity bill");
        expense5.setAmount(new BigDecimal("120.00"));
        expense5.setDate(today.minusDays(5));
        expense5.setCategory("Utilities");
        expenseRepository.save(expense5);
        
        Expense expense6 = new Expense();
        expense6.setDescription("New shoes");
        expense6.setAmount(new BigDecimal("89.99"));
        expense6.setDate(today.minusDays(6));
        expense6.setCategory("Shopping");
        expenseRepository.save(expense6);
        
        Expense expense7 = new Expense();
        expense7.setDescription("Doctor appointment");
        expense7.setAmount(new BigDecimal("75.00"));
        expense7.setDate(today.minusDays(7));
        expense7.setCategory("Healthcare");
        expenseRepository.save(expense7);
        
        Expense expense8 = new Expense();
        expense8.setDescription("Coffee and snacks");
        expense8.setAmount(new BigDecimal("12.50"));
        expense8.setDate(today.minusDays(8));
        expense8.setCategory("Food");
        expenseRepository.save(expense8);
        
        System.out.println("Sample expense data has been loaded!");
    }
} 