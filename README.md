# BANKING-SYSTEM
# 🏦 Banking System (Java + JDBC + MySQL)

This is a console-based Java application that simulates basic banking operations using JDBC to connect to a MySQL database.

---

## 🔧 Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- VS Code

---

## 📚 Features

- ➕ Create a new account
- 💰 Deposit money
- 💸 Withdraw money
- 🔍 Check account balance
- ❌ Exit the program

---

## 🛠️ MySQL Database Setup

Open MySQL Workbench or command-line and run:

```sql
CREATE DATABASE bankdb;
USE bankdb;

CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    balance DOUBLE
);

How to Run This Project

Clone this repository or download the ZIP

Download the MySQL JDBC driver from:
https://dev.mysql.com/downloads/connector/j/

Place the .jar file inside the lib/ folder

Compile using:

bash
Copy
Edit
javac -cp ".;lib/mysql-connector-j-9.4.0.jar" BankingSystem.java
Run using:

bash
Copy
Edit
java -cp ".;lib/mysql-connector-j-9.4.0.jar" BankingSystem
📌 Notes
Database credentials must match your local setup (edit inside the .java file).

This project is console-based (no GUI).

👨‍💻 Author
Name: Aditya

Internship Project: Cantilever Java Internship (3rd Year BTech IT)
