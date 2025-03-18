package models;

public class CheckingAccount extends BankAccount {

    public double minimumBalance;

    public CheckingAccount() {
        super();
        this.minimumBalance = 0.0; // TODO: Think of a default minimum balance
    }

    public CheckingAccount(String name) {
        super(name);
        this.minimumBalance = 0.0;
    }

    public CheckingAccount(String accountName, double minimumBalance) {
        super(accountName);
        this.minimumBalance = minimumBalance;
    }

    public CheckingAccount (int accountNo, String accountName, double minimumBalance) {
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

    public void encashCheck (double amount) {
        if (amount >= minimumBalance) {
            System.out.println("Encashing of ₱" + amount + " is successful");
            super.withdraw(amount);
        } else {
            System.out.println("Your withdrawal exceeds the minimum balance");
        }

    }
}
