package Task1;

import java.util.*;

// Represents a single transaction
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getDetails() {
        return type + ": $" + amount;
    }
}

// Represents the user’s bank account and operations
class BankAccount {
    private double balance;
    private List<Transaction> history;

    public BankAccount(double initialAmount) {
        this.balance = initialAmount;
        this.history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) {
        if (amt <= 0) {
            System.out.println("⚠️ Invalid deposit amount!");
            return;
        }
        balance += amt;
        history.add(new Transaction("Deposit", amt));
        System.out.println("✅ Amount deposited successfully.");
    }

    public boolean withdraw(double amt) {
        if (amt > balance) {
            System.out.println("❌ Not enough balance.");
            return false;
        }
        balance -= amt;
        history.add(new Transaction("Withdraw", amt));
        System.out.println("✅ Withdrawal successful.");
        return true;
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("📭 No transactions found.");
            return;
        }
        System.out.println("\n📜 Transaction History:");
        for (Transaction tx : history) {
            System.out.println(tx.getDetails());
        }
    }
}

// Represents a user with credentials and a bank account
class User {
    private String username;
    private String pin;
    private BankAccount account;

    public User(String username, String pin, double openingBalance) {
        this.username = username;
        this.pin = pin;
        this.account = new BankAccount(openingBalance);
    }

    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public String getUsername() {
        return username;
    }

    public BankAccount getAccount() {
        return account;
    }
}

// Main application class
public class ATMApp {
    private static Map<String, User> userMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Adding sample users
        userMap.put("user1", new User("user1", "1234", 1000));
        userMap.put("user2", new User("user2", "5678", 2000));

        System.out.print("🔐 Enter Username: ");
        String uname = sc.next();

        if (!userMap.containsKey(uname)) {
            System.out.println("❌ User not registered.");
            return;
        }

        User user = userMap.get(uname);
        System.out.print("🔑 Enter PIN: ");
        String enteredPin = sc.next();

        if (!user.checkPin(enteredPin)) {
            System.out.println("❌ Incorrect PIN entered.");
            return;
        }

        BankAccount acc = user.getAccount();
        System.out.println("\n✅ Welcome back, " + uname + "!");

        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Select option: ");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("💰 Your balance: $" + acc.getBalance());
                    break;
                case 2:
                    System.out.print("💵 Amount to deposit: ");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                    break;
                case 3:
                    System.out.print("💸 Amount to withdraw: ");
                    double wd = sc.nextDouble();
                    acc.withdraw(wd);
                    break;
                case 4:
                    acc.showHistory();
                    break;
                case 5:
                    System.out.println("👋 Thanks for using our ATM. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid choice, please try again.");
            }
        }
    }
}
