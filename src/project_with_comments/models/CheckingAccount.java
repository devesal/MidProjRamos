package project_with_comments.models;

/**
 * Represents a checking account in the Co-Pals Bank System.
 * This account type maintains a minimum balance requirement and provides check encashment functionality.
 * Extends the base BankAccount class to provide specialized checking account operations.
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
public class CheckingAccount extends BankAccount {

    /** The minimum balance that must be maintained in the account */
    public final double minimumBalance;

    /**
     * Default constructor that creates a new checking account with a default minimum balance.
     * Initializes the account with a minimum balance requirement of 500.0.
     */
    public CheckingAccount() {
        super();
        this.minimumBalance = 500.0;
    }

    /**
     * Creates a new checking account with specified parameters.
     *
     * @param accountNo The unique 9-digit account number
     * @param accountName The name of the account holder
     * @param minimumBalance The minimum balance requirement for this account
     */
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    /**
     * Gets the minimum balance requirement for this account.
     *
     * @return The minimum balance that must be maintained
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Returns the type of account as a string.
     *
     * @return A string indicating this is a checking account
     */
    @Override
    public String displayAccountType() {
        return "Checking Account";
    }

    /**
     * Processes a check encashment request while ensuring minimum balance requirements are met.
     * The transaction will be denied if:
     * - The amount exceeds the current balance
     * - The withdrawal would cause the balance to fall below the minimum requirement
     *
     * @param amount The amount to be encashed from the check
     */
    public void encashCheck(double amount) {
        double currentBalance = super.inquireBalance();

        if (amount > currentBalance) {
            System.out.println("❌ Insufficient funds to encash ₱" + amount);
            return;
        }

        // Check if withdrawal would violate minimum balance
        if ((currentBalance - amount) >= minimumBalance) {
            System.out.println("Encashing ₱" + amount + " successful");
            super.withdraw(amount);
        } else {
            System.out.println("❌ Encashment denied: Balance cannot go below (₱" + minimumBalance + ")");
        }
    }
}
