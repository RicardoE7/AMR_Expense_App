package com.amr.expensetracker.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	 private static final String URL = "jdbc:mysql://localhost:3306/PersonalFinance";
	    private static final String USER = "root"; // your MySQL username
	    private static final String PASS = "yourpassword"; // your MySQL password

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASS);
	    }
}
