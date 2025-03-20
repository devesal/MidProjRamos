/**
 * The BankController class manages the overall banking system operations,
 * including user authentication, account creation, transactions, and menu navigation.
 * @author AQUINO, THEO JAMES CORONEZA
 * @author ARELLANO, CLENDRICK JOSHUA MANGONON
 * @author MANGONON, JOHN CEDRICK GARCIA
 * @author ONG, RON MIGUEL CAU
 * @author RAMOS, RICKY MARC SALAZAR
 * @author ROSANA, JEAVEN VINCENT YOJAHN OPERIA
 * @version 1.0
 * @date 3/20/2025
 */
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
    /**
     * Starts the banking system, handling authentication and account-specific menus.
     */
    public void start() {
        boolean running = true;
        boolean isSignedIn = false;

        while (running) {

            do {
                System.out.println("\n=======================");
                System.out.println("WELCOME TO CO-PALS BANK");
                System.out.println("=======================");

                displayAuthMenu();

                switch (selectMenuOption(3)) {
                    case 1 -> isSignedIn = signIn();
                    case 2 -> displayAccCreationMenu();
                    case 3 -> running = false;
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
                }
            }
        }
    }
    /**
     * Displays the authentication menu for signing in or creating an account.
     */
    private void displayAuthMenu() {
        currentAccount = null;

        System.out.println("\n[1] Sign in");
        System.out.println("[2] Create Account");
        System.out.println("[3] Exit");
    }
    /**
     * Displays the account creation menu for different account types.
     */
    private void displayAccCreationMenu() {
        showAccountCreationOptions();

        switch (selectMenuOption(4)) {
            case 1 -> createAccount("Checking");
            case 2 -> createAccount("Credit");
            case 3 -> createAccount("Investment");
            case 4 -> {return;}
        }
    }
    /**
     * Handles user sign-in by verifying the provided account number.
     * @return true if sign-in is successful, false otherwise.
     */
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
    /**
     * Searches for an account by account number.
     * @param accountNo The account number to find.
     * @return true if the account is found, false otherwise.
     */
    private boolean findAccount(int accountNo) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNo() == accountNo) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }
    /**
     * Displays the menu for Checking Accounts and processes user selections.
     *
     * @return true if the user stays signed in, false if they exit.
     */
    private boolean displayCheckingAccMenu() {
        System.out.println("\n=======================");
        System.out.println("\n" + currentAccount.getAccountName());
        System.out.println(currentAccount.getAccountNo() + " - " + currentAccount.displayAccountType()+ "\n");

        System.out.println("[1] Balance Inquiry");
        System.out.println("[2] Deposit Transaction");
        System.out.println("[3] Transfer Money");
        System.out.println("[4] Display Account Information");
        System.out.println("[5] Close Account");
        System.out.println("[6] Exit");

        return selectCheckingAccOptions();
    }
    /**
     * Displays the menu for Credit Card Accounts and processes user selections.
     *
     * @return true if the user stays signed in, false if they exit.
     */
    private boolean displayCCAccMenu() {
        System.out.println("\n=======================");
        System.out.println("\n" + currentAccount.getAccountName());
        System.out.println(currentAccount.getAccountNo() + " - " + currentAccount.displayAccountType());
        System.out.println("Credit Limit: ₱" + ((CreditCardAccount) currentAccount).getCreditLimit() + "\n");

        System.out.println("[1] Inquire Available Credit");
        System.out.println("[2] Cash Advance");
        System.out.println("[3] Pay Card");
        System.out.println("[4] Charge to Card");
        System.out.println("[5] Display Account Information");
        System.out.println("[6] Close Account");
        System.out.println("[7] Exit");

        return selectCCAccOptions();
    }
    /**
     * Handles menu selection for Checking Accounts.
     *
     * @return true if the user stays signed in, false if they exit.
     */
    private boolean selectCheckingAccOptions() {
        switch (selectMenuOption(6)) {
            case 1 -> balanceInquiry();
            case 2 -> currentAccount.deposit(getDoubleInput("Enter amount to deposit: "));
            case 3 -> currentAccount.transferMoney(inputAccountNo(), getDoubleInput("\nTransfer Amount: "), bankAccounts);
            case 4 -> System.out.println(currentAccount.toString());
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
    /**
     * Handles menu selection for Credit Card Accounts.
     *
     * @return true if the user stays signed in, false if they exit.
     */
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
    /**
     * Handles menu selection for Investment Accounts.
     *
     * @return true if the user stays signed in, false if they exit.
     */
    private boolean selectInvestmentAccOptions() {
        switch (selectMenuOption(6)) {
            case 1 -> balanceInquiry();
            case 2 -> ((InvestmentAccount) currentAccount).addInvestment(getDoubleInput("Enter amount to invest: "));
            case 3 -> inquireInvestmentValue();
            case 4 -> System.out.println(currentAccount.toString());
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
    /**
     * Displays the menu for Investment Accounts and processes user selections.
     *
     * @return true if the user stays signed in, false if they exit.
     */
    private boolean displayInvestmentAccMenu() {
        System.out.println("\n=======================");

        System.out.println("\n" + currentAccount.getAccountName());
        System.out.println("#" + currentAccount.getAccountNo() + " - " + currentAccount.displayAccountType());
        System.out.println("Interest Rate: " + ((InvestmentAccount) currentAccount).getInterest() * 100 + "%");
        System.out.println("Minimum Balance: ₱" + ((InvestmentAccount) currentAccount).getMinimumBalance() + "\n");

        System.out.println("[1] Balance Inquiry");
        System.out.println("[2] Add Investment");
        System.out.println("[3] Inquire Investment Value");
        System.out.println("[4] Close Account");
        System.out.println("[5] Exit");

        return selectInvestmentAccOptions();
    }
    /**
     * Displays the account creation options to the user.
     */
    private void showAccountCreationOptions() {
        System.out.println("\n=====================");
        System.out.println("\nSELECT ACCOUNT TYPE");

        System.out.println("\n[1] Checking Account");
        System.out.println("[2] Credit Card Account");
        System.out.println("[3] Investment Account");
        System.out.println("[4] Back to Main Menu");
    }
    /**
     * Prompts the user for their first and last name and returns it as a formatted string.
     *
     * @return A string containing the user's full name.
     */
    private String inputName() {
        System.out.print("\nFirst Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        return String.format("%s %s", firstName, lastName);
    }
    /**
     * Prompts the user to enter an account number and validates it.
     * Ensures the account number is exactly 9 digits.
     *
     * @return A valid 9-digit account number entered by the user.
     */
    private int inputAccountNo() {
        while (true) {
            int input = getIntInput("\nAccount Number: ");

            if (input == 1 && currentAccount == null) {
                return input;
            }

            if (String.valueOf(input).length() != 9) {
                System.out.println("\n❌ Account number must be exactly 9 digits. Try again.");
                continue;
            }

            return input;
        }
    }
    /**
     * Creates a new account based on the specified type.
     * Ensures that the account number is unique before creation.
     *
     * @param type The type of account to create (Checking, Credit, Investment).
     */
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
            case "Checking" -> bankAccounts.add(new CheckingAccount(accountNo, name,  0.0));
            case "Credit" -> bankAccounts.add(new CreditCardAccount(accountNo, name, 25000, 0));
            case "Investment" -> bankAccounts.add(new InvestmentAccount(accountNo, name, 500, 0.35));
        }

        System.out.println("\n======================");
        System.out.println("\nAccount Created!");
        System.out.println("\n" + name);
        System.out.println("#" + accountNo);

        goBack();
    }
    /**
     * Displays a message prompting the user to press any key to go back.
     */
    private void goBack() {
        System.out.println("\nEnter any key to go back");
        scanner.nextLine();
    }
    /**
     * Displays the balance of the current account.
     */
    private void balanceInquiry() {
        System.out.println("\n======================");
        System.out.println("\nBALANCE INQUIRY");

        System.out.println("Your balance is ₱: " + currentAccount.inquireBalance());
    }
    /**
     * Displays the investment value of the current investment account.
     */
    private void inquireInvestmentValue() {
        System.out.println("\n======================");
        System.out.println("\nINQUIRE INVESTMENT VALUE");

        double investmentValue = ((InvestmentAccount) currentAccount).inquireInvestmentValue();
        System.out.println("Your investment value is: ₱" + investmentValue);
    }
    /**
     * Prompts user for input and validates menu selection within the given range.
     * @param max The maximum valid option number.
     * @return The selected menu option.
     */
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
    /**
     * Gets integer input from the user, ensuring a valid number is entered.
     * @param message The prompt message.
     * @return The validated integer input.
     */
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
    /**
     * Gets double input from the user, ensuring a valid number is entered.
     * @param message The prompt message.
     * @return The validated double input.
     */
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
    /**
     * The main method to run the banking system.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.start();
    }
}
