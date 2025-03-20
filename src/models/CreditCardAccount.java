package models;

import java.util.ArrayList;

/**
 * Represents a credit card account in the Co-Pals Bank System.
 * This account type provides credit card functionality including charging purchases,
 * making payments, and obtaining cash advances. Extends the base BankAccount class
 * to provide specialized credit card account functionality.
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
public class CreditCardAccount extends BankAccount {

    /** The maximum credit limit for this account */
    private double creditLimit;
    /** The current charges/balance on the credit card */
    private double charges;

    /**
     * Default constructor that creates a new credit card account.
     * Initializes with a default credit limit of 100,000 and zero charges.
     */
    public CreditCardAccount() {
        super();
        creditLimit = 100000;
        charges = 0.0;
    }

    /**
     * Creates a new credit card account with specified parameters.
     *
     * @param accountNo The unique 9-digit account number
     * @param accountName The name of the account holder
     * @param creditLimit The maximum credit limit for this account
     * @param charges The initial charges on the account
     */
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
        setAccountNo(accountNo);
        setAccountName(accountName);
    }

    /**
     * Gets the credit limit of the account.
     *
     * @return The maximum credit limit
     */
    public double getCreditLimit() {
        return this.creditLimit;
    }

    /**
     * Gets the current charges on the account.
     *
     * @return The current charges/balance
     */
    public double getCharges() {
        return this.charges;
    }

    /**
     * Makes a payment towards the credit card balance.
     * Payment cannot exceed the current charges.
     *
     * @param amount The amount to pay
     */
    public void payCard(double amount) {
        if (amount > charges) {
            System.out.println("❌ Payment exceeds total charges");
            return;
        } else {
            charges -= amount;
            System.out.println("Payment successful! Your remaining balance is: ₱" + charges);
        }
    }

    /**
     * Displays the available credit on the account.
     * Available credit is calculated as credit limit minus current charges.
     */
    public void inquireAvailableCredit() {
        double availableCredit = creditLimit - charges;
        System.out.println("Your available credit is: "+ availableCredit);
    }

    /**
     * Charges an amount to the credit card if sufficient credit is available.
     *
     * @param amount The amount to charge
     */
    public void chargeToCard(double amount) {
        double availableCredit = creditLimit - charges;
        if (availableCredit >= amount) {
            charges += amount;
            System.out.println("Charges successful, total charges: "+ charges);
        } else {
            System.out.println("❌ Not enough credit");
        }
    }

    /**
     * Gets a cash advance from the credit card account.
     * Cash advance is limited to 50% of available credit.
     *
     * @param amount The amount of cash advance requested
     */
    public void getCashAdvance(double amount) {
        double availableCredit = creditLimit - charges;
        availableCredit = availableCredit * 0.5;
        if (amount < availableCredit) {
            charges += amount;
            System.out.println("Cash advance approved! You have been charged: ₱" + amount);
        } else {
            System.out.println("❌ Transaction declined: Requested cash advance exceeds your available credit.");
        }
    }

    /**
     * Returns a string representation of the account.
     *
     * @return Formatted string with account details including credit limit
     */
    @Override
    public String toString() {
        return getAccountName() +
                "\n#" + getAccountNo() +
                "\nStatus: " + getStatus() +
                "\nCredit Limit: " + creditLimit;
    }

    /**
     * Closes the credit card account if all charges are paid.
     * Account cannot be closed with outstanding charges.
     *
     * @param bankAccounts List of bank accounts to remove this account from
     */
    @Override
    public void closeAccount(ArrayList<BankAccount> bankAccounts) {

        if (charges == 0) {
            System.out.println("Congratulations! You have achieved a Great Credit Score!");
        } else {
            System.out.println("❌ Please settle your remaining balance before closing your account");
            return;
        }

        bankAccounts.remove(this);
        super.setStatus("Closed");
        System.out.println("Your account has been closed.");
    }
}
