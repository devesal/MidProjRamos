package project.models;

import java.util.ArrayList;

public class BankAccount {
    private static int nextAccountNumber = 100000000;
    private int accountNo;
    private String accountName;
    private String status;
    private double balance;

    public BankAccount() {
        this.accountNo = nextAccountNumber++;
        accountName = "";
        status = "Active";
        balance = 0.0;
    }

    public BankAccount(int accountNo, String accountName) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        status = "Active";
        balance = 0.0;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void deposit(double amount) {
        this.balance = amount + balance;
        System.out.println("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            System.out.println("❌ Insufficient balance. Transaction terminated");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawal successful");
        System.out.println("₱" + amount + " has been withdrawn from #" + accountNo);
    }

    public double inquireBalance() {
        return balance;
    }

    public void transferMoney(int accountNumber, double amount, ArrayList<BankAccount> bankAccounts) {
        if (amount > balance) {
            System.out.println("❌ Invalid amount. Transaction terminated");
            return;
        }

        for (BankAccount recipient : bankAccounts) {
            if (recipient.getAccountNo() == accountNumber) {
                balance -= amount;
                recipient.deposit(amount);
                System.out.println("Transfer successful! $" + amount + " has been sent to account #" + recipient.getAccountNo());
                return;
            }
        }
        System.out.println("❌ Account not found");
    }

    public void closeAccount(ArrayList<BankAccount> bankAccounts) {
        if (balance > 0) {
            withdraw(balance);
        }
        else {
            System.out.println("❌ There is no balance to withdraw");
        }
        status = "Closed";
        System.out.println("This account has been closed");
    }

    public String displayAccountType() {
        return "Savings Account";
    }

    public String toString() {
        return accountName + "\n#" + accountNo + "\nStatus: " + status;
    }
} 