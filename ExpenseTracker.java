import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    static class Transaction {
        String type;  // "Income" or "Expense"
        String category;
        String bankName;
        double amount;

        public Transaction(String type, String category, String bankName, double amount) {
            this.type = type;
            this.category = category;
            this.bankName = bankName;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type + " | Bank: " + bankName + " | Category: " + category + " | Amount: $" + amount;
        }
    }

    private static double balance = 0.0;
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Enhanced Money and Expense Tracker ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. Filter Transactions by Category");
            System.out.println("5. View Summary (Income, Expenses, Balance)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addIncome(scanner);
                    break;
                case 2:
                    addExpense(scanner);
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    filterByCategory(scanner);
                    break;
                case 5:
                    viewSummary();
                    break;
                case 6:
                    System.out.println("Exiting... Thank you for using the Enhanced Expense Tracker!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addIncome(Scanner scanner) {
        System.out.print("Enter income amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();
        balance += amount;
        transactions.add(new Transaction("Income", "N/A", bankName, amount));
        System.out.println("Income added successfully.");
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter expense amount: $");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance! Expense cannot be added.");
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.print("Enter expense category (e.g., Food, Travel): ");
        String category = scanner.nextLine();
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();

        balance -= amount;
        transactions.add(new Transaction("Expense", category, bankName, amount));
        System.out.println("Expense added successfully.");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }

        System.out.println("\n--- Transaction History ---");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void filterByCategory(Scanner scanner) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.print("Enter category to filter by: ");
        String category = scanner.nextLine();

        System.out.println("\n--- Transactions in Category: " + category + " ---");
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.category.equalsIgnoreCase(category)) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found in this category.");
        }
    }

    private static void viewSummary() {
        double totalIncome = 0.0;
        double totalExpenses = 0.0;

        for (Transaction transaction : transactions) {
            if (transaction.type.equals("Income")) {
                totalIncome += transaction.amount;
            } else if (transaction.type.equals("Expense")) {
                totalExpenses += transaction.amount;
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Remaining Balance: $" + balance);
    }
}
