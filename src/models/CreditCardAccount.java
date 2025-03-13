package models;

public class CreditCardAccount extends BankAccount {
    private double creditLimit;
    private double charges;


    public CreditCardAccount(){

        creditLimit = 0.0;
        charges = 0.0;

    }
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges){
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
    }
    public double getCreditLimit(){
        return this.creditLimit;
    }
    public double getCharges(){
        return this.charges;
    }
    public void payCard(double amount){
        if (amount <= charges){
            charges -= amount;
            System.out.println("Successful, remaining charges: "+ charges);
        }
        else
            System.out.println("Payment is more than the charges");
    }
    public void inquireAvailableCredit(){
        double availableCredit = creditLimit - charges;
        System.out.println("Your available Credit is: "+availableCredit);
    }
    public void chargeToCard(double amount){
        double availableCredit = creditLimit - charges;
        if(availableCredit >= amount){
            charges += amount;
            System.out.println("Charges successful, total charges: "+charges);
        }
        else
            System.out.println("Not enough credit");
    }
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