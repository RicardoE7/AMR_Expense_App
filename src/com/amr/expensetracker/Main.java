package com.amr.expensetracker;

import com.amr.expensetracker.dao.ExpenseDAO;
import com.amr.expensetracker.model.expense.Expense;

public class Main {

	public static void main(String[] args) {
	        ExpenseDAO dao = new ExpenseDAO();

	        // Insert sample expense
	        Expense exp1 = new Expense("2025-07-09", "Food", 12.99, "Lunch sandwich");
	        dao.insertExpense(exp1);

	        // Retrieve total expenses by category
	        dao.getTotalExpensesByCategory();
	    }

}
