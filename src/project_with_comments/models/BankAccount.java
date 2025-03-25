package project_with_comments.models;

import java.util.ArrayList;

/**
 * The base class for all bank accounts in the Co-Pals Bank System.
 * This class provides the fundamental functionality for account management
 * including deposits, withdrawals, transfers, and account status tracking.
 *
 * Created on: 3/21/2025
 * 
 * @author Aquino, Theo James Coroneza
 * @author Arellano, Clendrick Joshua Mangonon
 * @author Mangonon, John Cedrick Garcia
 * @author Ong, Ron Miguel Cau
 * @author Ramos, Ricky Marc Salazar
 * @author Rosana, Jeaven Vincent Yojan Operia
 * @version 1.0
 */
public class BankAccount {

    /** Counter for generating unique account numbers */
    private static int nextAccountNumber = 100000000;
    /** Unique 9-digit account identifier */
    private int accountNo;
    /** Name of the account holder */
    private String accountName;
    /** Current balance in the account */
    private double balance;
    /** Current status of the account (Active/Closed) */
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

    public void deposit(double amount) {
        this.balance = amount + balance;
        System.out.println("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance = balance - amount;
            System.out.println("Money withdrawn: ₱" + amount);
        } else {
            System.out.println("❌ Insufficient balance. Transaction terminated");
        }
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
        bankAccounts.remove(this);
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
