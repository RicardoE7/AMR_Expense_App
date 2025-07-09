# AMR Expense Tracker

## Overview
This Expense Tracker application was developed as a demonstration project for Actuarial Management Resources (AMR). It showcases a full workflow for expense management, from data entry and visualization to export and business reporting, using a modern, maintainable technology stack.

---

## Purpose
- **Demonstrate technical and analytical skills** relevant to actuarial and business operations.
- **Showcase end-to-end workflow**: data capture, visualization, export, and integration with Excel for actuarial/business analysis.
- **Highlight best practices** in Java, web development, and data handling.

---

## Features
- **Java Backend**
  - JDBC connection to MySQL database
  - CRUD operations for expenses (insert, retrieve, group by category)
  - CSV export functionality for easy reporting and integration
- **Modern JavaScript Frontend**
  - Clean, responsive HTML/CSS UI
  - Chart.js pie chart for visualizing expenses by category
  - Add new expenses via the web interface (with validation)
  - Download CSV on demand
  - AMR logo branding for a professional look
- **Excel Integration (VBA Macro)**
  - One-click macro to import CSV and generate a summary pie chart
  - No duplicate slices—categories are automatically summed
  - Designed for actuarial/business users familiar with Excel

---

## Technology Stack
- **Java 17** (core logic, backend, CSV export)
- **MySQL** (data storage)
- **HTML5, CSS3, JavaScript** (frontend)
- **Chart.js** (data visualization)
- **VBA Macro** (Excel integration)

---

## How It Works
1. **User adds expenses** via the web frontend or Java backend.
2. **Data is stored** in a MySQL database.
3. **Expenses can be exported** to a CSV file for reporting or further analysis.
4. **Frontend visualizes** expenses by category in real time (Chart.js pie chart).
5. **Excel macro** allows business/actuarial users to import the CSV and instantly generate a summary chart for presentations or further actuarial analysis.

---

## Setup & Usage

### 1. **Java Backend**
- Configure your MySQL credentials in `src/application.properties`.
- Compile Java code:
  ```sh
  javac -d bin src/com/amr/expensetracker/Main.java src/com/amr/expensetracker/dao/ExpenseDAO.java src/com/amr/expensetracker/model/expense/Expense.java src/com/amr/expensetracker/util/DBUtil/DBUtil.java
  ```
- Run the app:
  ```sh
  java -cp "lib/mysql-connector-java-8.0.33.jar;bin" com.amr.expensetracker.Main
  ```
- This will insert sample data, show totals, and export `expenses.csv`.

### 2. **Frontend**
- Open `index.html` in your browser.
- Add new expenses, view the pie chart, and download the CSV as needed.
- The AMR logo is displayed for branding.

### 3. **Excel Integration**
- Open Excel and run the provided VBA macro (`ImportExpensesAndCreateChart`).
- The macro will prompt you to select `expenses.csv`, import the data, and generate a pie chart with one slice per category.

---

## Why This Is Relevant for AMR
- **Data-driven decision making:** The app demonstrates the ability to collect, process, and visualize data—core to actuarial work.
- **Workflow automation:** Shows how to bridge operational data with business reporting (Excel), a common actuarial need.
- **Clean code and best practices:** Java, SQL, and web code are organized, maintainable, and secure (e.g., prepared statements).
- **Professional presentation:** AMR branding, clear UI, and Excel integration make it business-ready.
- **Extensible:** The architecture allows for easy addition of features (e.g., real-time backend, authentication, advanced analytics).

---

## Notes
- For MVP, real-time backend updates from the frontend are not implemented, but the architecture supports easy future integration.
- The app is designed for demonstration and can be extended for production use.

---

## Contact
**Developed by:** [Ricardo Edwards]

If you have questions or would like to discuss this project further, please contact me at [ricardo.edwards4899@gmail.com]. 