package com.amr.expensetracker;

import com.amr.expensetracker.dao.ExpenseDAO;
import com.amr.expensetracker.model.expense.Expense;
import com.amr.expensetracker.util.DBUtil.DBUtil;

public class Main {

	public static void main(String[] args) {
	        ExpenseDAO dao = new ExpenseDAO();

	        // Insert sample expenses
	        System.out.println("Adding sample expenses to database...");
	        
	        Expense exp1 = new Expense("2025-01-15", "Food", 12.99, "Lunch sandwich");
	        dao.insertExpense(exp1);
	        
	        Expense exp2 = new Expense("2025-01-15", "Transportation", 25.50, "Gas station");
	        dao.insertExpense(exp2);
	        
	        Expense exp3 = new Expense("2025-01-16", "Entertainment", 15.00, "Movie ticket");
	        dao.insertExpense(exp3);
	        
	        Expense exp4 = new Expense("2025-01-16", "Food", 8.75, "Coffee and pastry");
	        dao.insertExpense(exp4);
	        
	        Expense exp5 = new Expense("2025-01-17", "Shopping", 45.99, "Groceries");
	        dao.insertExpense(exp5);
	        
	        Expense exp6 = new Expense("2025-01-17", "Transportation", 12.00, "Uber ride");
	        dao.insertExpense(exp6);
	        
	        Expense exp7 = new Expense("2025-01-18", "Food", 22.50, "Dinner at restaurant");
	        dao.insertExpense(exp7);
	        
	        Expense exp8 = new Expense("2025-01-18", "Entertainment", 30.00, "Concert tickets");
	        dao.insertExpense(exp8);

	        System.out.println("Sample expenses added successfully!");
	        System.out.println();

	        // Retrieve total expenses by category
	        dao.getTotalExpensesByCategory();
	        System.out.println();
	        
	        // Export to CSV using path from properties file
	        String csvPath = DBUtil.getCsvExportPath();
	        System.out.println("Exporting data to: " + csvPath);
	        dao.exportToCSV(csvPath);
	    }

}
