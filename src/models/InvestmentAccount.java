package models;

public class InvestmentAccount extends BankAccount {

    private double minimumBalance;
    private double interest;

    public InvestmentAccount(){
        minimumBalance = 0.0;
        interest = 0.0;
    }

    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest){
        super(accountNo, accountName, 0.0, "Active");
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterest() {
        return interest;
    }

    public void addInvestment(double amount){
        super.deposit(amount);
    }

    public void inquireInvestmentValue(){
        double investmentValue = super.inquireBalance() * (1 + interest);
        System.out.println("Your investment value is: " + investmentValue);
    }

    public void closeAccount() {
        double finalBalance = super.inquireBalance() * (1 + interest);
        if (finalBalance > 0) {
            withdraw(finalBalance);
        } else {
            System.out.println("There is no balance to withdraw.");
        }

        System.out.println("This account has been closed. Remaining balance has been withdrawn.");
        super.closeAccount();
    }

}
