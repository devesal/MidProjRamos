package models;

import java.util.ArrayList;

/**
 * Represents an Investment Account, which extends the functionality of a Bank Account.
 * It includes features such as a minimum balance requirement and an interest rate.
 */
public class InvestmentAccount extends BankAccount {

    /** The minimum balance required to maintain the investment account. */
    private final double minimumBalance;

    /** The interest rate applied to the investment account balance. */
    private final double interest;

    /**
     * Default constructor initializing minimum balance and interest to zero.
     */
    public InvestmentAccount() {
        minimumBalance = 0.0;
        interest = 0.0;
    }

    /**
     * Constructs an InvestmentAccount with specified details.
     *
     * @param accountNo       The account number.
     * @param accountName     The name associated with the account.
     * @param minimumBalance  The minimum balance required.
     * @param interest        The interest rate applied.
     */
    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }

    /**
     * Gets the minimum balance required for the investment account.
     *
     * @return The minimum balance.
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Gets the interest rate applied to the investment account.
     *
     * @return The interest rate.
     */
    public double getInterest() {
        return interest;
    }

    /**
     * Displays the type of the account.
     *
     * @return A string representing the type of account.
     */
    @Override
    public String displayAccountType() {
        return "Investment Account";
    }

    /**
     * Adds an investment by depositing the specified amount into the account.
     *
     * @param amount The amount to be added as an investment.
     */
    public void addInvestment(double amount) {
        super.deposit(amount);
    }

    /**
     * Inquires the balance including the minimum balance requirement.
     *
     * @return The total balance available.
     */
    public double inquireBalance() {
        return super.inquireBalance() + minimumBalance;
    }

    /**
     * Inquires the total investment value including interest earned.
     *
     * @return The investment value including interest.
     */
    public double inquireInvestmentValue() {
        System.out.println("Your interest rate is: " + interest * 100 + "%");
        System.out.println("Total Earned Interest: ₱" + String.format("%.2f", inquireBalance() * interest));
        double investmentValue = Double.parseDouble(String.format("%.2f", (inquireBalance() + minimumBalance) * (1 + interest)));
        return investmentValue;
    }

    /**
     * Closes the investment account, withdrawing the final balance with interest.
     *
     * @param bankAccounts The list of bank accounts from which this account should be removed.
     */
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

    /**
     * Returns a string representation of the InvestmentAccount including account details.
     *
     * @return A formatted string with account details.
     */
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