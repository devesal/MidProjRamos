package models;

/**
 * Midterm Project: User-defined Reference Classes, Inheritance, and Exception Handling
 * Represents a Checking Account, which extends the BankAccount class.
 * This account requires a minimum balance and allows check encashment.
 * @author AQUINO, THEO JAMES CORONEZA
 * @author ARELLANO, CLENDRICK JOSHUA MANGONON
 * @author MANGONON, JOHN CEDRICK GARCIA
 * @author ONG, RON MIGUEL CAU
 * @author RAMOS, RICKY MARC SALAZAR
 * @author ROSANA, JEAVEN VINCENT YOJAHN OPERIA
 * @version 1.0
 * @date 3/20/2025
 */
public class CheckingAccount extends BankAccount {

    /** The minimum balance required for the account. */
    public double minimumBalance;

    /**
     * Default constructor initializing a Checking Account with a default minimum balance of ₱5000.0.
     */
    public CheckingAccount() {
        super();
        this.minimumBalance = 5000.0;
    }

    /**
     * Parameterized constructor for initializing a Checking Account with custom values.
     *
     * @param accountNo The account number.
     * @param accountName The name associated with the account.
     * @param minimumBalance The minimum balance required for the account.
     */
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    /**
     * Retrieves the minimum balance required for the account.
     *
     * @return The minimum balance.
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Displays the type of account.
     *
     * @return A string representing the account type.
     */
    @Override
    public String displayAccountType() {
        return "Checking Account";
    }

    /**
     * Allows encashment of a check if the balance meets the minimum requirement.
     *
     * @param amount The amount to be encashed.
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
