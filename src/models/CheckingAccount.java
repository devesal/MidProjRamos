package models;

public class CheckingAccount extends BankAccount {
    public double minimumBalance;

    public CheckingAccount () {
        this.minimumBalance = 0;
    }

    public CheckingAccount (int accountNo, String accountName, double minimumBalance) {
        super();
        this.minimumBalance = minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void encashCheck (double amount) {
        if (amount >= minimumBalance) {
            System.out.println("Encashing of " + amount + " pesos is successful");
            withdraw(amount);
        } else  {
            System.out.println("Your withdrawal exceeds the minimum balance");
        }

    }
}
