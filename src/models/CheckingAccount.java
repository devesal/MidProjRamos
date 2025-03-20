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
        System.out.println("❌ Withdrawal is not allowed with a checking account");
    }

    @Override
    public String displayAccountType() {
        return "Checking Account";
    }

    public void encashCheck (double amount) {
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
