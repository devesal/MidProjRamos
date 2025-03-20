package models;

public class CreditCardAccount extends BankAccount {

    private double creditLimit;
    private double charges;

    public CreditCardAccount() {
        super();
        creditLimit = 100000;
        charges = 0.0;
    }

    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
        setAccountNo(accountNo);
        setAccountName(accountName);
    }

    public double getCreditLimit() {
        return this.creditLimit;
    }

    public double getCharges() {
        return this.charges;
    }

    // TODO: Handle input validation in main method
    public void payCard(double amount) {
        charges -= amount;
        System.out.println("Payment successful! Your remaining balance is: ₱" + charges);
    }

    public void inquireAvailableCredit() {
        double availableCredit = creditLimit - charges;
        System.out.println("Your available credit is: "+ availableCredit);
    }

    public void chargeToCard(double amount) {
        double availableCredit = creditLimit - charges;
        if (availableCredit >= amount) {
            charges += amount;
            System.out.println("Charges successful, total charges: "+ charges);
        } else {
            System.out.println("❌ Not enough credit");
        }
    }

    @Override
    public String displayAccountType() {
        return "Credit Card Account";
    }

    public void getCashAdvance(double amount) {
        double availableCredit = creditLimit - charges;
        availableCredit = availableCredit * 0.5;
        if (amount < availableCredit) {
            charges += amount;
            System.out.println("Cash advance approved! Your new total balance is: ₱" + charges);
        } else {
            System.out.println("❌ Transaction declined: Requested cash advance exceeds your available credit.");
        }
    }

    @Override
    public String toString() {
        return "Account No: " + getAccountNo() + "\nAccount Name: " +
                getAccountName() + "\nCredit Limit: " + creditLimit +
                "\nCharges: " + charges;
    }
}
