import java.sql.*;
import java.util.Scanner;

public class BankingSystem {
    static final String URL = "jdbc:mysql://localhost:3306/bankdb";
    static final String USER = "root";         // Your MySQL username
    static final String PASS = "25July@2k25"; // Your MySQL password

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            while (true) {
                System.out.println("\n--- Simple Banking System ---");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> deposit();
                    case 3 -> withdraw();
                    case 4 -> checkBalance();
                    case 5 -> {
                        System.out.println("Exiting...");
                        conn.close();
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createAccount() throws SQLException {
        System.out.print("Enter your name: ");
        sc.nextLine(); // flush
        String name = sc.nextLine();
        String query = "INSERT INTO accounts (name, balance) VALUES (?, 0)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("Account created successfully!");
    }

    static void deposit() throws SQLException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        String query = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        int updated = ps.executeUpdate();

        if (updated > 0)
            System.out.println("Deposit successful.");
        else
            System.out.println("Account not found.");
    }

    static void withdraw() throws SQLException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        String checkQuery = "SELECT balance FROM accounts WHERE account_id = ?";
        PreparedStatement checkPs = conn.prepareStatement(checkQuery);
        checkPs.setInt(1, id);
        ResultSet rs = checkPs.executeQuery();

        if (rs.next()) {
            double balance = rs.getDouble("balance");
            if (balance >= amount) {
                String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
                PreparedStatement ps = conn.prepareStatement(withdrawQuery);
                ps.setDouble(1, amount);
                ps.setInt(2, id);
                ps.executeUpdate();
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    static void checkBalance() throws SQLException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        String query = "SELECT name, balance FROM accounts WHERE account_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Balance: Rs" + rs.getDouble("balance"));
        } else {
            System.out.println("Account not found.");
        }
    }
}



