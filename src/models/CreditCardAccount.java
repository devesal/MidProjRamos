/**
 * The CreditCardAccount class extends BankAccount and represents a credit card account.
 * This account allows credit-based transactions such as charges, payments, and cash advances.
 *
 */
package models;

public class CreditCardAccount extends BankAccount {
    private double creditLimit;
    private double charges;

    /**
     * Default constructor initializes credit limit and charges to 0.
     */
    public CreditCardAccount(){

        creditLimit = 0.0;
        charges = 0.0;

    }
    /**
     * Constructor to initialize a credit card account with specific values.
     *
     * @param accountNo    The account number
     * @param accountName  The account holder's name
     * @param creditLimit  The credit limit of the card
     * @param charges      The current charges on the card
     */
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges){
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
        String status = getStatus();
    }
    /**
     * Gets the credit limit of the account.
     *
     * @return The credit limit
     */
    public double getCreditLimit(){
        return this.creditLimit;
    }
    /**
     * Gets the current charges on the account.
     *
     * @return The total charges
     */
    public double getCharges(){
        return this.charges;
    }
    /**
     * Pays off part or all of the credit card charges.
     *
     * Algorithm:
     * 1. Check if the payment amount is less than or equal to the charges.
     * 2. If true, deduct the amount from charges and print success message.
     * 3. If false, print an error message indicating overpayment.
     *
     * @param amount The amount to be paid
     */
    public void payCard(double amount){
        if (amount <= charges){
            charges -= amount;
            System.out.println("Successful, remaining charges: "+ charges);
        }
        else
            System.out.println("Payment is more than the charges");
    }
    /**
     * Displays the available credit by subtracting charges from the credit limit.
     */
    public void inquireAvailableCredit(){
        double availableCredit = creditLimit - charges;
        System.out.println("Your available Credit is: "+availableCredit);
    }
    /**
     * Charges an amount to the credit card if sufficient credit is available.
     *
     * Algorithm:
     * 1. Calculate available credit (credit limit - charges).
     * 2. If available credit is greater than or equal to the charge amount, add amount to charges.
     * 3. If not, print an error message indicating insufficient credit.
     *
     * @param amount The amount to be charged to the card
     */
    public void chargeToCard(double amount){
        double availableCredit = creditLimit - charges;
        if(availableCredit >= amount){
            charges += amount;
            System.out.println("Charges successful, total charges: "+charges);
        }
        else
            System.out.println("Not enough credit");
    }
    /**
     * Allows the user to take a cash advance up to 50% of the available credit.
     *
     * Algorithm:
     * 1. Calculate available credit (credit limit - charges) and determine the cash advance limit (50% of available credit).
     * 2. If requested amount is within the cash advance limit, add the amount to charges.
     * 3. If not, print an error message indicating excess request.
     *
     * @param amount The amount to be withdrawn as a cash advance
     */
    public void getCashAdvance(double amount){
        double availableCredit = creditLimit - charges;
        availableCredit = availableCredit * 0.5;
        if(amount < availableCredit){
            charges += amount;
            System.out.println("Cash advance successful, total charge: "+charges);
        }
        else
            System.out.println("Cash advance exceeds the required Cash advance limit");
    }
}