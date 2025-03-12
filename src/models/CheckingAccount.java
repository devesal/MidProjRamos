package models;

//let me know if there are any issues
//or new additions you would like to see/ are needed :>

public class CheckingAccount extends BankAccounts {
    public double minimumBalance;

    public CheckingAccount () {
        this.minimumBalance = 0;
    }

    public CheckingAccount (int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName, 0.0, "active");
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
