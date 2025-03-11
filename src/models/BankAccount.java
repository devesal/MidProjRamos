package models;

public class BankAccount {
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;

    public BankAccount(){
        accountNo = 100000000;
        accountName = "";
        balance = 0.0;
        status = "Active";

    }
    public int getAccountNo(){
        return accountNo;
    }
    public String getAccountName(){
        return accountName;
    }
    public String getStatus(){
        return status;
    }
    public void setAccountNo(int accountNo){
        this.accountNo = accountNo;
    }
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }
    public String toString(){
        return "Account Created:\nAccount Name: "+accountName+"\nAccount Number: "+accountNo+"\nBalance: "+balance+"\nStatus: "+status;
    }

    public void deposit(double amount){
        this.balance = amount + balance;
        System.out.print("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }

    public void withdraw(double amount){
        if(balance >= amount) {
            this.balance = balance - amount;
            System.out.print("Money has been withdrawn from your account");
            System.out.println("Please check your account for safety measures");
        }
        if(balance < amount){
            System.out.print("Insufficient balance. Transaction terminated");
        }
    }

    public double inquireBalance(){
        System.out.print("Your balance is: "+balance);
        return balance;
    }

    public void transferMoney(int accountNo, double amount, BankAccount[]listOfBankAccounts){
        boolean accountFound = false;
        for (BankAccount account : listOfBankAccounts) {
            if (account.getAccountNo() == accountNo && account.getStatus().equals("Active")) {
                accountFound = true;
                break;
            }
        }
        if (accountFound) {
            if (amount <= balance) {
                balance -= amount;
                for (BankAccount account : listOfBankAccounts) {
                    if (account.getAccountNo() == accountNo) {
                        account.deposit(amount);
                        System.out.println("Transfer successful.");
                    }
                }
            } else {
                System.out.println("Insufficient balance. Transfer transaction terminated.");
            }
        } else {
            System.out.println("Account not found. Transfer transaction terminated.");
        }

    }

    public void closeAccount(){
        if(balance >0){
            System.out.print("Please withdraw your remaining balance before closing your account");
        }
        else{
            this.status = "Closed";
            System.out.print("This account has been closed");
        }
    }
}
