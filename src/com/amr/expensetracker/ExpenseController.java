package com.amr.expensetracker;

import com.amr.expensetracker.dao.ExpenseDAO;
import com.amr.expensetracker.model.expense.Expense;
import com.amr.expensetracker.util.DBUtil.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseController {
    private ExpenseDAO expenseDAO;
    
    public ExpenseController() {
        this.expenseDAO = new ExpenseDAO();
    }
    
    public void handleRequest(String method, String path, Map<String, String> params, PrintWriter out) {
        try {
            if (method.equals("GET") && path.equals("/expenses")) {
                // Get all expenses
                List<Expense> expenses = expenseDAO.getAllExpenses();
                sendJsonResponse(out, convertExpensesToJson(expenses));
            } else if (method.equals("POST") && path.equals("/expenses")) {
                // Add new expense
                Expense newExpense = new Expense(
                    params.get("date"),
                    params.get("category"),
                    Double.parseDouble(params.get("amount")),
                    params.get("description")
                );
                expenseDAO.insertExpense(newExpense);
                sendJsonResponse(out, "{\"message\":\"Expense added successfully\"}");
            } else if (method.equals("GET") && path.equals("/export")) {
                // Export to CSV
                String csvPath = DBUtil.getCsvExportPath();
                expenseDAO.exportToCSV(csvPath);
                sendJsonResponse(out, "{\"message\":\"CSV exported successfully\",\"file\":\"" + csvPath + "\"}");
            } else {
                sendJsonResponse(out, "{\"error\":\"Endpoint not found\"}");
            }
        } catch (Exception e) {
            sendJsonResponse(out, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    private String convertExpensesToJson(List<Expense> expenses) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < expenses.size(); i++) {
            Expense exp = expenses.get(i);
            json.append("{");
            json.append("\"id\":").append(i + 1).append(",");
            json.append("\"date\":\"").append(exp.getDate()).append("\",");
            json.append("\"category\":\"").append(exp.getCategory()).append("\",");
            json.append("\"amount\":").append(exp.getAmount()).append(",");
            json.append("\"description\":\"").append(exp.getDescription()).append("\"");
            json.append("}");
            if (i < expenses.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }
    
    private void sendJsonResponse(PrintWriter out, String json) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: application/json");
        out.println("Access-Control-Allow-Origin: *");
        out.println("Access-Control-Allow-Methods: GET, POST, OPTIONS");
        out.println("Access-Control-Allow-Headers: Content-Type");
        out.println("Content-Length: " + json.length());
        out.println();
        out.println(json);
    }
} 