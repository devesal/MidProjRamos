package models;

public class CheckingAccount extends BankAccount {
    public final double minimumBalance;

    public CheckingAccount() {
        super();
        this.minimumBalance = 500.0;
    }

    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public String displayAccountType() {
        return "Checking Account";
    }

    public void encashCheck(double amount) {
        double currentBalance = super.inquireBalance();

        if (amount > currentBalance) {
            System.out.println("❌ Insufficient funds to encash ₱" + amount);
            return;
        }

        if ((currentBalance - amount) >= minimumBalance) {
            System.out.println("Encashing ₱" + amount + " successful");
            super.withdraw(amount);
        } else {
            System.out.println("❌ Encashment denied: Balance cannot go below (₱" + minimumBalance + ")");
        }
    }
} 