package main;

import models.BankAccount;
import models.CheckingAccount;
import models.CreditCardAccount;
import models.InvestmentAccount;

import java.util.ArrayList;
import java.util.Scanner;

public class BankController {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private BankAccount currentAccount;

    public void start() {
        boolean isSignedIn = false;

        while (true) {
            do {
                System.out.println("\n=======================");
                System.out.println("WELCOME TO CO-PALS BANK");
                System.out.println("=======================");

                displayAuthMenu();

                switch (selectMenuOption(3)) {
                    case 1 -> isSignedIn = signIn();
                    case 2 -> displayAccCreationMenu();
                    case 3 -> {
                        System.out.println("\nThank you for using Co-Pals Bank!");
                        System.exit(0);
                    }
                }
            } while (!isSignedIn);

            boolean staySignedIn = true;
            while (staySignedIn) {
                if (currentAccount instanceof CheckingAccount) {
                    staySignedIn = displayCheckingAccMenu();
                } else if (currentAccount instanceof CreditCardAccount) {
                    staySignedIn = displayCCAccMenu();
                } else if (currentAccount instanceof InvestmentAccount) {
                    staySignedIn = displayInvestmentAccMenu();
                } else {
                    staySignedIn = displaySavingsAccountMenu();
                }
            }
        }
    }

    private void displayAuthMenu() {
        System.out.println("\n[1] Sign in");
        System.out.println("[2] Create Account");
        System.out.println("[3] Exit");
    }

    private void displayAccCreationMenu() {
        showAccountCreationOptions();

        switch (selectMenuOption(5)) {
            case 1 -> createAccount("Savings");
            case 2 -> createAccount("Checking");
            case 3 -> createAccount("Credit");
            case 4 -> createAccount("Investment");
            case 5 -> {return;}
        }
    }

    private boolean signIn() {

        System.out.println("\n=======================");
        System.out.println("\nSIGN IN");
        System.out.println("[1] Go Back");

        while (true) {
            int accountNo = inputAccountNo();
            boolean isAccountFound = findAccount(accountNo);

            if (accountNo == 1) {
                return false;
            }

            if (isAccountFound) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("❌ Account not found. Try again.");
            }
        }
    }

    private boolean findAccount(int accountNo) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNo() == accountNo) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    private boolean displaySavingsAccountMenu() {
        System.out.println("\n=======================");
        System.out.println("\n" + currentAccount.getAccountName());
        System.out.println(currentAccount.getAccountNo() + " - " + currentAccount.displayAccountType());

        System.out.println("\n[1] Balance Inquiry");
        System.out.println("[2] Deposit Transaction");
        System.out.println("[3] Withdraw Transaction");
        System.out.println("[4] Transfer Money");
        System.out.println("[5] Display Information");
        System.out.println("[6] Close Account");
        System.out.println("[7] Sign out");

        return selectSavingsAccOptions();
    }

    private boolean selectSavingsAccOptions() {
        switch (selectMenuOption(7)) {
            case 1 -> balanceInquiry();
            case 2 -> currentAccount.deposit(getDoubleInput("Enter amount to deposit: "));
            case 3 -> currentAccount.withdraw(getDoubleInput("Enter amount to withdraw: "));
            case 4 -> currentAccount.transferMoney(inputAccountNo(), getDoubleInput("\nTransfer Amount: "), bankAccounts);
            case 5 -> System.out.println(currentAccount.toString());
            case 6 -> {
                currentAccount.closeAccount(bankAccounts);
                goBack();
                return false;
            }
            case 7 -> {
                return false;
            }
        }
        goBack();
        return true;
    }

    private boolean displayCheckingAccMenu() {
        System.out.println("\n=======================");
        System.out.println("\n" + currentAccount.getAccountName());
        System.out.println(currentAccount.getAccountNo() + " - " + currentAccount.displayAccountType());

        System.out.println("\n[1] Balance Inquiry");
        System.out.println("[2] Deposit Transaction");
        System.out.println("[3] Transfer Money");
        System.out.println("[4] Encash Check");
        System.out.println("[5] Display Information");
        System.out.println("[6] Close Account");
        System.out.println("[7] Sign out");

        return selectCheckingAccOptions();
    }

    private boolean displayCCAccMenu() {
        System.out.println("\n=======================");
        System.out.println("\n" + currentAccount.getAccountName());
        System.out.println(currentAccount.getAccountNo() + " - " + currentAccount.displayAccountType());
        System.out.println("Credit Limit: ₱" + ((CreditCardAccount) currentAccount).getCreditLimit() + "\n");

        System.out.println("[1] Inquire Available Credit");
        System.out.println("[2] Cash Advance");
        System.out.println("[3] Pay Card");
        System.out.println("[4] Charge to Card");
        System.out.println("[5] Display Information");
        System.out.println("[6] Close Account");
        System.out.println("[7] Sign out");

        return selectCCAccOptions();
    }

    private boolean selectCheckingAccOptions() {
        switch (selectMenuOption(7)) {
            case 1 -> balanceInquiry();
            case 2 -> currentAccount.deposit(getDoubleInput("Enter amount to deposit: "));
            case 3 -> currentAccount.transferMoney(inputAccountNo(), getDoubleInput("\nTransfer Amount: "), bankAccounts);
            case 4 -> ((CheckingAccount)currentAccount).encashCheck(getDoubleInput("Enter amount to encash: "));
            case 5 -> System.out.println(currentAccount.toString());
            case 6 -> {
                currentAccount.closeAccount(bankAccounts);
                goBack();
                return false;
            }
            case 7 -> {
                return false;
            }
        }
        goBack();
        return true;
    }

    private boolean selectCCAccOptions() {
        switch (selectMenuOption(7)) {
            case 1 -> ((CreditCardAccount) currentAccount).inquireAvailableCredit();
            case 2 -> ((CreditCardAccount) currentAccount).getCashAdvance(getDoubleInput("Enter cash advance amount: "));
            case 3 -> ((CreditCardAccount) currentAccount).payCard(getDoubleInput("Enter amount to pay: "));
            case 4 -> ((CreditCardAccount) currentAccount).chargeToCard(getDoubleInput("Enter amount to charge: "));
            case 5 -> System.out.println(currentAccount.toString());
            case 6 -> {
                currentAccount.closeAccount(bankAccounts);
                goBack();
                return false;
            }
            case 7 -> {
                return false;
            }
        }
        goBack();
        return true;
    }

    private boolean selectInvestmentAccOptions() {
        switch (selectMenuOption(6)) {
            case 1 -> balanceInquiry();
            case 2 -> ((InvestmentAccount) currentAccount).addInvestment(getDoubleInput("Enter amount to invest: "));
            case 3 -> inquireInvestmentValue();
            case 4 -> System.out.println(currentAccount);
            case 5 -> {
                currentAccount.closeAccount(bankAccounts);
                goBack();
                return false;
            }
            case 6 -> {
                return false;
            }
        }
        goBack();
        return true;
    }

    private boolean displayInvestmentAccMenu() {
        System.out.println("\n=======================");

        System.out.println("\n" + currentAccount.getAccountName() + " - " + currentAccount.displayAccountType());
        System.out.println("Minimum Balance: ₱" + ((InvestmentAccount) currentAccount).getMinimumBalance());

        System.out.println("\n[1] Balance Inquiry");
        System.out.println("[2] Add Investment");
        System.out.println("[3] Inquire Investment Value");
        System.out.println("[4] Display Information");
        System.out.println("[5] Close Account");
        System.out.println("[6] Sign out");

        return selectInvestmentAccOptions();
    }

    private void showAccountCreationOptions() {
        System.out.println("\n=====================");
        System.out.println("\nSELECT ACCOUNT TYPE");

        System.out.println("\n[1] Savings Account");
        System.out.println("[2] Checking Account");
        System.out.println("[3] Credit Card Account");
        System.out.println("[4] Investment Account");
        System.out.println("[5] Back to Main Menu");
    }

    private String inputName() {
        while (true) {
            System.out.print("\nFirst Name: ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine().trim();
            
            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("❌ Name cannot be empty");
                continue;
            }
            if (!firstName.matches("[a-zA-Z ]+") || !lastName.matches("[a-zA-Z ]+")) {
                System.out.println("❌ Name can only contain letters");
                continue;
            }
            return String.format("%s %s", firstName, lastName);
        }
    }

    private int inputAccountNo() {
        while (true) {
            int input = getIntInput("\nAccount Number: ");

            if (input == 1 && currentAccount == null) { // PROBLEM: remove currentAccount == null
                return input;
            }

            if (String.valueOf(input).length() != 9) {
                System.out.println("\n❌ Account number must be exactly 9 digits. Try again.");
                continue;
            }

            return input;
        }
    }

    private void createAccount(String type) {
        System.out.println("\n=======================");
        System.out.println("\nCREATE ACCOUNT");

        String name = inputName();
        int accountNo;

        while (true) {
            accountNo = inputAccountNo();
            boolean isAccountFound = findAccount(accountNo);

            if (isAccountFound) {
                System.out.println("\n❌ Account number already exists. Try a different one.");
                continue;
            }
            break;
        }

        switch (type) {
            case "Savings" -> bankAccounts.add(new BankAccount(accountNo, name));
            case "Checking" -> bankAccounts.add(new CheckingAccount(accountNo, name,  500.0));
            case "Credit" -> bankAccounts.add(new CreditCardAccount(accountNo, name, 25000, 0));
            case "Investment" -> bankAccounts.add(new InvestmentAccount(accountNo, name, 5000, 0.35));
        }

        System.out.println("\n======================");
        System.out.println("\nAccount Created!");
        System.out.println("\n" + name);
        System.out.println("#" + accountNo);

        goBack();
    }

    private void goBack() {
        System.out.println("\nEnter any key to go back");
        scanner.nextLine();
    }

    private void balanceInquiry() {
        System.out.println("\n======================");
        System.out.println("\nBALANCE INQUIRY");

        System.out.println("Your balance is: ₱" + currentAccount.inquireBalance());
    }

    private void inquireInvestmentValue() {
        System.out.println("\n======================");
        System.out.println("\nINQUIRE INVESTMENT VALUE");

        double investmentValue = ((InvestmentAccount) currentAccount).inquireInvestmentValue();
        System.out.println("Your investment value is: ₱" + investmentValue);
    }

    private int selectMenuOption(int max) {
        while (true) {
            System.out.println("\n=======================");

            int input = getIntInput("\nSELECT OPTION: ");
            if (input >= 1 && input <= max) {
                return input;
            }

            System.out.println("❌ Invalid option. Please enter a number between 1 and " + max + ".");
        }
    }

    private int getIntInput(String message) {
        while (true) {
            System.out.print(message);

            try {
                int input = Integer.parseInt(scanner.nextLine());

                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("❌ Input must not have a negative number.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }
    }

    private double getDoubleInput(String message) {
        while (true) {
            System.out.print(message);

            try {
                double input = Double.parseDouble(scanner.nextLine());

                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("❌ Input must not have a negative number.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.start();
    }
} 