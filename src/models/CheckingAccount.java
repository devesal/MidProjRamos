package models;

public class CheckingAccount extends BankAccount {

    public double minimumBalance;

    public CheckingAccount() {
        super();
        this.minimumBalance = 0.0; // TODO: Think of a default minimum balance
    }

    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawal is not allowed with a checking account");
        System.out.println("Please use the encash check option instead.");
    }

    @Override
    public String displayAccountType() {
        return "Checking Account";
    }

    public void encashCheck (double amount) {
        if (amount >= minimumBalance) {
            System.out.println("Encashing of ₱" + amount + " is successful");
            super.withdraw(amount);
        } else {
            System.out.println("Your withdrawal exceeds the minimum balance");
        }

    }
}
