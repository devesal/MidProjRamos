package models;

public class InvestmentAccount extends BankAccount {

    private double minimumBalance;
    private double interest;

    public InvestmentAccount(int accountNo, String accountName, String status) {
        super(accountNo, accountName, status);
        minimumBalance = 0.0;
        interest = 0.0;
        status = "Active";
    }

    public InvestmentAccount(double minimumBalance, double interest) {
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterest() {
        return interest;
    }

    public void addInvestment(double amount) {
        super.deposit(amount);
    }

    public void inquireInvestmentValue() {
        double investmentValue = super.inquireBalance() * (1 + interest);
        System.out.println("Your investment value is: " + investmentValue);
    }

    public void transferMoney(int accountNo, double amount, BankAccount[]listOfBankAccounts) {
        System.out.println("You cannot transfer money from an investment account.");
    }

    public void withdraw(double amount) {
        System.out.println("You cannot withdraw money from an investment account.");
        System.out.println("Please close your account to withdraw your investment.");
    }

    public void closeAccount() {
        double finalBalance = super.inquireBalance() * (1 + interest);
        if (finalBalance > 0) {
            withdraw(finalBalance);
            super.setStatus("Closed");
            System.out.println("Your account has been closed.");

        } else {
            System.out.println("There is no balance to withdraw.");
            super.setStatus("Closed");
            System.out.println("Your account has been closed.");
        }
    }
}
