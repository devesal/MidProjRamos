package models;

import java.util.ArrayList;

/**
 * Represents an investment account in the Co-Pals Bank System.
 * This account type provides investment functionality with interest accrual
 * and minimum balance requirements. The account earns interest on the invested
 * amount and requires maintaining a minimum balance.
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
public class InvestmentAccount extends BankAccount {

    /** The minimum balance that must be maintained in the account */
    private final double minimumBalance;
    /** The annual interest rate applied to the investment (as a decimal) */
    private final double interest;

    /**
     * Default constructor that creates a new investment account.
     * Initializes with zero minimum balance and interest rate.
     */
    public InvestmentAccount() {
        minimumBalance = 0.0;
        interest = 5000.0;
    }

    /**
     * Creates a new investment account with specified parameters.
     *
     * @param accountNo The unique 9-digit account number
     * @param accountName The name of the account holder
     * @param minimumBalance The minimum balance requirement
     * @param interest The annual interest rate (as a decimal)
     */
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

        if (finalBalance > minimumBalance * (1 + interest)) {
            System.out.println("Investment has been withdrawn");
            System.out.println("You have deposited ₱" + String.format("%.2f", finalBalance) + " and earned ₱" + String.format("%.2f", (finalBalance - inquireBalance())));
        } else {
            System.out.println("❌ You cannot withdraw the minimum balance");
            return;
        }
        bankAccounts.remove(this);
        super.setStatus("Closed");
        System.out.println("Your account has been closed.");
    }

    @Override
    public String toString() {
        return getAccountName() + "\n" +
                getAccountNo() + "\n" +
                "Interest: " + interest * 100 + "%\n" +
                "Status: " + getStatus() + "\n";
    }
}
