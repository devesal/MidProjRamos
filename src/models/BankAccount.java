/**
 * The BankAccount class represents a basic bank account with functionalities
 * like deposit, withdrawal, balance inquiry, money transfer, and account closure.
 */
package models;

public class BankAccount {

    private static int nextAccountNumber = 100000000;
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;
    /**
     * Default constructor initializes account with default values.
     */
    public BankAccount() {
        this.accountNo = nextAccountNumber++;
        accountName = "";
        balance = 0.0;
        status = "Active";
    }
    /**
     * Constructor to initialize a bank account with an account number and name.
     * Balance is initialized to 0.0 and status is set to active.
     *
     * @param accountNo   The account number
     * @param accountName The account holder's name
     */
    public BankAccount(int accountNo, String accountName) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        balance = 0.0;
        status = "Active";
    }
    /**
     * Constructor to initialize a bank account with a specified status.
     *
     * @param accountNo   The unique account number.
     * @param accountName The account holder's name.
     * @param status      The initial status of the account.
     */
    public BankAccount(int accountNo, String accountName, String status) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.status = status;
    }
    /**
     * Sets the account number.
     *
     * @param accountNo The unique account number to be assigned.
     */
    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
    /**
     * Gets the account number.
     *
     * @return The account number.
     */
    public int getAccountNo() {
        return accountNo;
    }
    /**
     * Sets the account status.
     *
     * @param status The status of the account (Active or Closed).
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Gets the current account status.
     *
     * @return The status of the account (Active or Closed).
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the account holder's name.
     *
     * @param accountName The name of the account holder.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**
     * Gets the account holder's name.
     *
     * @return The name of the account holder.
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * Returns a string line of the bank account.
     * @return A formatted string displaying account information.
     */
    public String toString() {
        return "\nAccount Name: "+accountName+"\nAccount Number: "+accountNo+"\nBalance: "+balance+"\nStatus: "+status;
    }
    /**
     * Deposits a specified amount into the account.
     * 1. Add the deposit amount to the current balance.
     * 2. Display a confirmation message.
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        this.balance = amount + balance;
        System.out.print("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }
    /**
     * Withdraws a specified amount from the account if sufficient balance exists.
     * 1. Check if balance is greater than or equal to the withdrawal amount.
     * 2. If true, deduct the amount from balance and display success message.
     * 3. If false, display an error message.
     *
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance = balance - amount;
            System.out.print("Money has been withdrawn from your account");
            System.out.println("Please check your account for safety measures");
        }
        if (balance < amount) {
            System.out.print("Insufficient balance. Transaction terminated");
        }
    }
    /**
     * Displays the current balance of the account.
     * Returns the account balance.
     * @return The account balance.
     */
    public double inquireBalance() {
        System.out.print("Your balance is: ₱"+ balance);
        return balance;
    }
    /**
     * Transfers money to another account if the target account exists and there is sufficient balance.
     * 1. Check if the target account exists in the list and is active.
     * 2. If found, check if the balance is sufficient.
     * 3. If true, deduct the amount and add it to the recipient's balance.
     * 4. Otherwise, display an appropriate error message.
     * @param recipientAccNo The recipient's account number.
     * @param amount         The amount to transfer.
     * @param bankAccounts   The list of available bank accounts.
     */
    public void transferMoney(int recipientAccNo, double amount, BankAccount[] bankAccounts) {

        boolean isAccountFound = false;
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNo() == recipientAccNo) {

                // TODO: Allow array to only have active accounts
                if (account.getStatus().equals("Closed")) {
                    System.out.println("Account is closed");
                }

                isAccountFound = true;
            }

            if (isAccountFound) {
                balance -= amount;
                account.deposit(amount);
                System.out.println("Transfer successful! $" + amount + " has been sent to account #" + account.getAccountNo());
                return;
            }
        }

        System.out.println("Account not found");
    }
    /**
     * Closes the account if there is no remaining balance.
     * 1. Check if balance is greater than 0.
     * 2. If true, request user to withdraw remaining balance first.
     * 3. If false, change account status to "Closed" and confirm closure.
     */
    public void closeAccount() {
        if (balance > 0) {
            System.out.print("Please withdraw your remaining balance before closing your account");
        }
        else {
            status = "Closed";
            System.out.print("This account has been closed");
        }
    }
}
