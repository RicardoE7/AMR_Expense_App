package com.amr.expensetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amr.expensetracker.model.expense.Expense;
import com.amr.expensetracker.util.DBUtil.DBUtil;

public class ExpenseDAO {
	public void insertExpense(Expense expense) {
        String sql = "INSERT INTO Expenses (date, category, amount, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, expense.getDate());
            stmt.setString(2, expense.getCategory());
            stmt.setDouble(3, expense.getAmount());
            stmt.setString(4, expense.getDescription());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Expense inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void getTotalExpensesByCategory() {
	    String sql = "SELECT category, SUM(amount) AS total FROM Expenses GROUP BY category";

	    try (Connection conn = DBUtil.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        System.out.println("Expenses by category:");
	        while (rs.next()) {
	            String category = rs.getString("category");
	            double total = rs.getDouble("total");
	            System.out.println(category + ": $" + total);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
