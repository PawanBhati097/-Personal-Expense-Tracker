import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Expense {
    private String category;
    private double amount;
    private String description;

    public Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

public class PersonalExpenseTracker {
    private ArrayList<Expense> expenses;
    private JFrame frame;
    private JTextField categoryField;
    private JTextField amountField;
    private JTextField descriptionField;
    private DefaultListModel<String> expensesListModel;

    public PersonalExpenseTracker() {
        expenses = new ArrayList<>();
        createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        frame = new JFrame("Personal Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Create expenses list
        expensesListModel = new DefaultListModel<>();
        JList<String> expensesList = new JList<>(expensesListModel);
        frame.add(new JScrollPane(expensesList), BorderLayout.CENTER);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });
        buttonPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Expense");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteExpense(expensesList);
            }
        });
        buttonPanel.add(deleteButton);

        JButton summaryButton = new JButton("Expense Summary");
        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expenseSummary();
            }
        });
        buttonPanel.add(summaryButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void addExpense() {
        String category = categoryField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String description = descriptionField.getText();

        Expense expense = new Expense(category, amount, description);
        expenses.add(expense);
        expensesListModel.addElement("Category: " + category + ", Amount: " + amount + ", Description: " + description);

        categoryField.setText("");
        amountField.setText("");
        descriptionField.setText("");

        System.out.println("Expense added successfully.");
    }

    private void deleteExpense(JList<String> expensesList) {
        int selectedIndex = expensesList.getSelectedIndex();
        if (selectedIndex != -1) {
            expenses.remove(selectedIndex);
            expensesListModel.remove(selectedIndex);
            System.out.println("Expense deleted successfully.");
        } else {
            System.out.println("No expense selected to delete.");
        }
    }

    private void expenseSummary() {
        double totalSpending = 0;
        for (Expense expense : expenses) {
            totalSpending += expense.getAmount();
        }
        JOptionPane.showMessageDialog(frame, "Total spending: $" + totalSpending);
    }

    public static void main(String[] args) {
        new PersonalExpenseTracker();
    }
}
