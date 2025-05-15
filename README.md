# 💼 Java Internship Projects @ Brainwave Matrix Solutions

Welcome to my internship repository! This showcases two Java projects completed during my internship at **Brainwave Matrix Solutions Pvt. Ltd.**  

---

## 🔹 Task 1: ATM Simulator System (Java CLI)
*A console-based ATM system with user authentication, transaction history, and account management.*

### 🎯 **Features**
- **User Authentication**: Secure PIN verification
- **Transaction Operations**:  
  💵 Deposit funds  
  💸 Withdraw funds (with balance checks)  
- **History Tracking**: View all past transactions
- **Multi-User Support**: Pre-loaded sample accounts (`user1:1234`, `user2:5678`)

### 🛠️ **Tech Stack**
- **Java** (OOP principles)
- **Collections Framework**: `HashMap` for user storage, `ArrayList` for transaction history

### 📂 **Code Structure**
src/Task1/
├── ATMApp.java // Main application logic
├── User.java // User credentials & account
├── BankAccount.java // Balance & transaction ops
└── Transaction.java // Transaction records


### 🖥️ **Demo**
```plaintext
🔐 Enter Username: user1
🔑 Enter PIN: 1234

--- ATM Menu ---
1. Check Balance
2. Deposit
3. Withdraw
4. View Transactions
5. Exit

```
---

## 🔹Task 2: Hospital Management System (Java + MySQL) 

*A modular system for managing healthcare workflows with 6 core modules.*

### 🎯 Features
Patient & Staff Management: CRUD operations

Appointment Scheduling: Date-based bookings

Inventory Alerts: Low-stock warnings

Billing & EHR: Integrated patient records

🛠️ Tech Stack
Java (JDBC, OOP)

MySQL: Relational database design

MVC Pattern: DAO, Models, Utils layers

### ![Screenshot 2025-05-14 211147](https://github.com/user-attachments/assets/d62aed1a-51bd-4519-9d75-3a91da7e546b)
📂 Project Structure
```src/
├── com.hospital_management_system/
│   ├── dao/           // Database operations
│   ├── model/         // Entity classes
│   ├── operations/    // CLI interface
│   └── utils/        // Utilities (DBConnection.java)
```

### 🚀 How to Run?
ATM System
```bash
cd Task1/
javac ATMApp.java
java ATMApp
```

### Hospital System.
Import hospital_db.sql into MySQL.

Update DBConnection.java with your credentials.

Run:

```bash
javac com/hospital_management_system/operations/Main.java
java com.hospital_management_system.operations.Main
```
### 📌 Key Learnings
✅ OOP Mastery: Encapsulation, modular design

✅ Database Integration: JDBC, SQL queries

✅ Error Handling: Input validation, transaction rollbacks

✅ CLI Development: User-friendly interfaces
