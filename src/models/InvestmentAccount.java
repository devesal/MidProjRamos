package models;

import java.util.ArrayList;

public class InvestmentAccount extends BankAccount {

    private final double minimumBalance;
    private final double interest;

    public InvestmentAccount() {
        minimumBalance = 0.0;
        interest = 0.0;
    }

    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterest() {
        return interest;
    }

    @Override
    public String displayAccountType() {
        return "Investment Account";
    }

    public void addInvestment(double amount) {
        super.deposit(amount);
    }

    public double inquireBalance() {
        return super.inquireBalance() + minimumBalance;
    }

    public double inquireInvestmentValue() {
        System.out.println("Your interest rate is: " + interest * 100 + "%");
        System.out.println("Total Earned Interest: ₱" + String.format("%.2f", inquireBalance() * interest));
        double investmentValue = Double.parseDouble(String.format("%.2f", (inquireBalance() + minimumBalance) * (1 + interest)));

        return investmentValue;
    }

    @Override
    public void closeAccount(ArrayList<BankAccount> bankAccounts) {
        double finalBalance = inquireBalance() * (1 + interest);

        if (finalBalance > minimumBalance) {
            System.out.println("Investment has been withdrawn");
            System.out.println("You have deposited ₱" + String.format("%.2f", finalBalance) + " and earned ₱" + String.format("%.2f", (finalBalance - inquireBalance())));
        } else {
            System.out.println("❌ You cannot withdraw the minimum balance");
        }

        bankAccounts.remove(this);
        super.setStatus("Closed");
        System.out.println("Your account has been closed.");
    }

    @Override
    public String toString() {
        return "Account No: " + getAccountNo() + "\n" +
                "Account Name: " + getAccountName() + "\n" +
                "Minimum Balance: ₱" + minimumBalance + "\n" +
                "Interest: " + interest + "\n" +
                "Status: " + getStatus() + "\n" +
                "Balance: ₱" + inquireInvestmentValue();
    }
}
