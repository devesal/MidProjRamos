/**
 * The InvestmentAccount class extends BankAccount and represents an investment account.
 * This account type has a minimum balance requirement and accrues interest.
 * Withdrawals and transfers are not allowed.
 */
package models;

public class InvestmentAccount extends BankAccount {

    private double minimumBalance;
    private double interest;
    /**
     * Default constructor initializes minimum balance and interest to 0.
     */
    public InvestmentAccount() {
        minimumBalance = 0.0;
        interest = 0.0;
    }
    /**
     * Constructor to initialize an investment account with specific values.
     *
     * @param accountNo      The account number
     * @param accountName    The account holder's name
     * @param minimumBalance The minimum balance required for the account
     * @param interest       The interest rate (e.g., 10% should be expressed as 0.10)
     */
    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }
    /**
     * Gets the minimum balance required for the account.
     *
     * @return The minimum balance
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }
    /**
     * Gets the interest rate of the investment account.
     *
     * @return The interest rate
     */
    public double getInterest() {
        return interest;
    }
    /**
     * Adds an investment amount to the account balance.
     *
     * @param amount The amount to be added to the investment
     */
    public void addInvestment(double amount) {
        super.deposit(amount);
    }
    /**
     * Calculates and displays the current investment value, including interest.
     * 1. Retrieve the current balance from the bank account.
     * 2. Multiply balance by (1 + interest) to calculate the investment value.
     * 3. Display the investment value.
     */
    public void inquireInvestmentValue() {
        double investmentValue = super.inquireBalance() * (1 + interest);
        System.out.println("Your investment value is: " + investmentValue);
    }
    /**
     * Does not allow money transfer from an investment account.
     * @param accountNo          Target account number
     * @param amount            Amount to transfer
     * @param listOfBankAccounts List of existing accounts
     */
    public void transferMoney(int accountNo, double amount, BankAccount[]listOfBankAccounts) {
        System.out.println("You cannot transfer money from an investment account.");
    }
    /**
     * Does not allow withdrawal from an investment account.
     * Instead, the account must be closed to access funds.
     *
     * @param amount The amount to be withdrawn
     */
    @Override
    public void withdraw(double amount) {
        System.out.println("You cannot withdraw money from an investment account.");
        System.out.println("Please close your account to withdraw your investment.");
    }
    /**
     * Closes the investment account and withdraws the total balance with interest.
     * 1. Calculate final balance including interest.
     * 2. If balance is greater than 0, withdraw the full amount and mark account as closed.
     * 3. If balance is 0, close the account immediately.
     */
    @Override
    public void closeAccount() {
        double finalBalance = super.inquireBalance() * (1 + interest);
        if (finalBalance > 0) {
            super.withdraw(finalBalance);
        } else {
            System.out.println("There is no balance to withdraw.");
        }
        super.setStatus("Closed");
        System.out.println("Your account has been closed.");
    }
}
