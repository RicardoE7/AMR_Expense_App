package com.amr.expensetracker.util.DBUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final Properties properties = new Properties();
    
    static {
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("Unable to find application.properties");
                
                properties.setProperty("db.url", "jdbc:mysql://localhost:3306/PersonalFinance");
                properties.setProperty("db.username", "root");
                properties.setProperty("db.password", "yourpassword");
                properties.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Error loading application.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Load the driver
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Please add mysql-connector-java to your classpath.", e);
        }
        
        return DriverManager.getConnection(
            properties.getProperty("db.url"),
            properties.getProperty("db.username"),
            properties.getProperty("db.password")
        );
    }
    
    public static String getCsvExportPath() {
        return properties.getProperty("csv.export.path", "expenses.csv");
    }
}
