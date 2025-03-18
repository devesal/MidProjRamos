package models;

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

    public BankAccount(String accountName) {
        this.accountNo = nextAccountNumber++;
        this.accountName = accountName;
        this.balance = 0.0;
        this.status = "Active";
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
        return "Account Created:\nAccount Name: "+accountName+"\nAccount Number: "+accountNo+"\nBalance: "+balance+"\nStatus: "+status;
    }

    public void deposit(double amount) {
        this.balance = amount + balance;
        System.out.print("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }

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

    public double inquireBalance() {
        System.out.print("Your balance is: ₱"+ balance);
        return balance;
    }

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

        System.out.println("No such bank account found");
    }

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
