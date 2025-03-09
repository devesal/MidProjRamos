package models;

public class BankAccount {
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;

    public BankAccounts(){
        accountNo = 100000000;
        accountName = "";
        balance = 0.0;
        status = "Closed";

    }
    public BankAccounts(int accountNo, String name, double balance, String status){
        this.accountNo = accountNo;
        this.accountName = name;
        this.balance = balance;
        this.status = status;
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
        return "Account Created"; //unsure yet
    }

    public void deposit(double amount){
        this.balance = amount + balance;
        System.out.print("Money has been deposited to your account");
        System.out.println("Please check your account for safety measures");
    }

    public void withdraw(double amount){
        this.balance = balance - amount;
        System.out.print("Money has been withdrawn from your account");
        System.out.println("Please check your account for safety measures");
    }

    public double inquireBalance(){
        return balance;
    }

    public void transferMoney(){
        //to be figured out and continued on IntelliJ. Work In Progress
    }

    public void closeAccount(){
        if(balance >0){
            System.out.print("Please withdraw your remaining balance before closing your account");
        }
        if(balance < 0){
            this.status = "Closed";
            System.out.print("This account has been closed");
        }
    }
}
