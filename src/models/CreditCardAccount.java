package models;

import java.util.ArrayList;
/**
 * Midterm Project: User-defined Reference Classes, Inheritance, and Exception Handling
 * Represents a credit card account, extending the BankAccount class.
 * Allows users to manage charges, payments, credit limit, and cash advances.
 * @author AQUINO, THEO JAMES CORONEZA
 * @author ARELLANO, CLENDRICK JOSHUA MANGONON
 * @author MANGONON, JOHN CEDRICK GARCIA
 * @author ONG, RON MIGUEL CAU
 * @author RAMOS, RICKY MARC SALAZAR
 * @author ROSANA, JEAVEN VINCENT YOJAHN OPERIA
 * @version 1.0
 * @date 3/20/2025
 */
public class CreditCardAccount extends BankAccount {

    private double creditLimit;
    private double charges;
    /**
     * Default constructor initializing a credit card account with a default credit limit of 100,000 and no charges.
     */
    public CreditCardAccount() {
        super();
        creditLimit = 100000;
        charges = 0.0;
    }
    /**
     * Parameterized constructor to initialize a credit card account with specific details.
     *
     * @param accountNo   The unique account number.
     * @param accountName The name associated with the account.
     * @param creditLimit The maximum credit limit for the account.
     * @param charges     The initial charges on the credit card.
     */
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
        setAccountNo(accountNo);
        setAccountName(accountName);
    }
    /**
     * Retrieves the credit limit of the account.
     *
     * @return The credit limit.
     */
    public double getCreditLimit() {
        return this.creditLimit;
    }
    /**
     * Retrieves the current charges on the credit card.
     *
     * @return The total charges.
     */
    public double getCharges() {
        return this.charges;
    }
    /**
     * Processes a payment towards the credit card balance.
     *
     * @param amount The amount to be paid.
     */
    // TODO: Handle input validation in main method
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
     * Displays the available credit on the credit card.
     */
    public void inquireAvailableCredit() {
        double availableCredit = creditLimit - charges;
        System.out.println("Your available credit is: "+ availableCredit);
    }
    /**
     * Charges an amount to the credit card, if the credit limit allows it.
     *
     * @param amount The amount to be charged.
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
     * Retrieves the type of account as a string.
     *
     * @return A string indicating this is a Credit Card Account.
     */
    @Override
    public String displayAccountType() {
        return "Credit Card Account";
    }
    /**
     * Allows the user to take a cash advance from their available credit.
     *
     * @param amount The amount requested for cash advance.
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
     * Returns a string representation of the credit card account details.
     *
     * @return A formatted string containing account information.
     */
    @Override
    public String toString() {
        return "Account No: " + getAccountNo() + "\nAccount Name: " +
                getAccountName() + "\nCredit Limit: " + creditLimit +
                "\nCharges: " + charges;
    }
    /**
     * Closes the account if the charges have been fully paid off.
     *
     * @param bankAccounts The list of bank accounts to remove this account from.
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
