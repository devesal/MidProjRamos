package models;

import java.util.ArrayList;

public class BankAccount {

    private static int nextAccountNumber = 100000000;
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;

    public BankAccount() {
        this.accountNo = nextAccountNumber++;
        accountName = "";
        balance = 0.0;
        status = "Active";
    }

    public BankAccount(int accountNo, String accountName) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        balance = 0.0;
        status = "Active";
    }

    public BankAccount(int accountNo, String accountName, String status) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.status = status;
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

    public String toString() {
        return "\nAccount Name: "+accountName+"\nAccount Number: "+accountNo+"\nBalance: "+balance+"\nStatus: "+status;
    }

    public void deposit(double amount) {
        this.balance = amount + balance;
        System.out.println("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance = balance - amount;
            System.out.println("Money has been withdrawn from your account");
            System.out.println("Please check your account for safety measures");
        }
        if (balance < amount) {
            System.out.println("Insufficient balance. Transaction terminated");
        }
    }

    public double inquireBalance() {
        System.out.println("Your balance is: ₱"+ balance);
        return balance;
    }

    public void transferMoney(int accountNumber, double amount, ArrayList<BankAccount> bankAccounts) {

        for (BankAccount recipient : bankAccounts) {
            if (recipient.getAccountNo() == accountNumber) {
                balance -= amount;
                recipient.deposit(amount);
                System.out.println("Transfer successful! $" + amount + " has been sent to account #" + recipient.getAccountNo());
                return;
            }
        }

        System.out.println("Account not found");
    }

    public void closeAccount(ArrayList<BankAccount> bankAccounts) {
        if (balance > 0) {
            System.out.println("Please withdraw your remaining balance before closing your account");
        }
        else {
            bankAccounts.remove(this);
            status = "Closed";
            System.out.println("This account has been closed");
        }
    }

    public String displayAccountType() {
        return "Bank Account";
    }
}
