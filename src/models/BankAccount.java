/**
 * Represents a bank account with basic functionalities such as deposit, withdrawal,
 * balance inquiry, money transfer, and account closure.
 * @author AQUINO, THEO JAMES CORONEZA
 * @author ARELLANO, CLENDRICK JOSHUA MANGONON
 * @author MANGONON, JOHN CEDRICK GARCIA
 * @author ONG, RON MIGUEL CAU
 * @author RAMOS, RICKY MARC SALAZAR
 * @author ROSANA, JEAVEN VINCENT YOJAHN OPERIA
 * @version 1.0
 * @date 3/20/2025
 */
package models;

import java.util.ArrayList;

public class BankAccount {

    private static int nextAccountNumber = 100000000;
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;
    /**
     * Default constructor that initializes a bank account with a unique account number
     * and sets the initial status to "Active".
     */
    public BankAccount() {
        this.accountNo = nextAccountNumber++;
        accountName = "";
        balance = 0.0;
        status = "Active";
    }
    /**
     * Constructs a bank account with a specified account number and account name.
     * @param accountNo The account number.
     * @param accountName The account holder's name.
     */
    public BankAccount(int accountNo, String accountName) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        balance = 0.0;
        status = "Active";
    }
    /**
     * Constructs a bank account with a specified account number, account name, and status.
     * @param accountNo The account number.
     * @param accountName The account holder's name.
     * @param status The status of the account (e.g., Active, Closed).
     */
    public BankAccount(int accountNo, String accountName, String status) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.status = status;
    }
    /**
     * Sets the account number.
     * @param accountNo The new account number.
     */
    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
    /**
     * Gets the account number.
     * @return The account number.
     */
    public int getAccountNo() {
        return accountNo;
    }
    /**
     * Sets the account status.
     * @param status The new account status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Gets the account status.
     * @return The account status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the account name.
     * @param accountName The new account name.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**
     * Gets the account name.
     * @return The account name.
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * Deposits a specified amount into the account.
     * @param amount The amount to be deposited.
     */
    public void deposit(double amount) {
        this.balance = amount + balance;
        System.out.println("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }
    /**
     * Withdraws a specified amount from the account if there are sufficient funds.
     * @param amount The amount to be withdrawn.
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance = balance - amount;
            System.out.println("Money withdrawn: ₱" + amount);
        } else {
            System.out.println("❌ Insufficient balance. Transaction terminated");
        }
    }
    /**
     * Retrieves the current balance of the account.
     * @return The account balance.
     */
    public double inquireBalance() {
        return balance;
    }
    /**
     * Transfers money to another bank account if the recipient exists.
     * @param accountNumber The account number of the recipient.
     * @param amount The amount to be transferred.
     * @param bankAccounts The list of all bank accounts.
     */
    public void transferMoney(int accountNumber, double amount, ArrayList<BankAccount> bankAccounts) {
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
    /**
     * Closes the account by withdrawing the remaining balance and updating the account status.
     * @param bankAccounts The list of all bank accounts.
     */
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
    /**
     * Displays the type of the bank account.
     * @return The type of account.
     */
    public String displayAccountType() {
        return "Bank Account";
    }
    /**
     * Returns a string representation of the bank account.
     * @return A formatted string containing account details.
     */
    public String toString() {
        return accountName + "\n#" + accountNo + "\nStatus: " + status;
    }
}
