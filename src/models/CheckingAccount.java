package models;

public class CheckingAccount extends BankAccount {
    public double minimumBalance;

    public CheckingAccount () {
        this.minimumBalance = 0;
    }

    public CheckingAccount (int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName, "active");
        this.minimumBalance = minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
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

    public void encashCheck (double amount) {
        if (amount >= minimumBalance) {
            System.out.println("Encashing of " + amount + " pesos is successful");
            super.withdraw(amount);
        } else  {
            System.out.println("Your withdrawal exceeds the minimum balance");
        }

    }
}
