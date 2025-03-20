/**
 * The CheckingAccount class extends BankAccount and represents a checking account.
 * This account type has a minimum balance requirement and allows check encashment.
 */
package models;

public class CheckingAccount extends BankAccount {

    public double minimumBalance;
    /**
     * Default constructor initializes minimum balance to 0.
     */
    public CheckingAccount() {
        super();
        this.minimumBalance = 0.0; // TODO: Think of a default minimum balance
    }
    /**
     * Constructor to initialize a checking account with account details and minimum balance.
     *
     * @param accountNo      The account number
     * @param accountName    The account holder's name
     * @param minimumBalance The minimum balance required for the account
     */
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }
    /**
     * Sets the minimum balance for the account.
     *
     * @return minimumBalance
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }
    /**
     * Overrides the withdraw method to prevent direct withdrawals from a Checking Account.
     * Instead, users are advised to use the "cash a check" option for withdrawals.
     *
     * @param amount The amount the user wants to withdraw (not applicable for checking accounts)
     */

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawal is not allowed with a checking account");
        System.out.println("Please use the encash check option instead.");
    }
    /**
     * Allows check encashment if the amount does not violate the minimum balance requirement.
     * 1. Check if the requested amount is greater than or equal to the minimum balance.
     * 2. If true, proceed with the withdrawal and print a success message.
     * 3. If false, display an error message stating that the withdrawal exceeds the minimum balance.
     * @param amount The amount to be withdrawn via check encashment
     */
    public void encashCheck (double amount) {
        if (amount >= minimumBalance) {
            System.out.println("Encashing of ₱" + amount + " is successful");
            super.withdraw(amount);
        } else {
            System.out.println("Your withdrawal exceeds the minimum balance");
        }

    }
}
