package project_with_comments.main;

import project_with_comments.models.BankAccount;
import project_with_comments.models.CheckingAccount;
import project_with_comments.models.CreditCardAccount;
import project_with_comments.models.InvestmentAccount;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main controller class for the Co-Pals Bank System.
 * Handles all user interactions, account management, and banking operations.
 * Provides functionality for account creation, authentication, and various
 * banking transactions across different account types.
 *
 * Created on: 3/21/2025
 * 
 * @author Aquino, Theo James Coroneza
 * @author Arellano, Clendrick Joshua Mangonon
 * @author Mangonon, John Cedrick Garcia
 * @author Ong, Ron Miguel Cau
 * @author Ramos, Ricky Marc Salazar
 * @author Rosana, Jeaven Vincent Yojan Operia
 * @version 1.0
 */
public class BankController {

    /** Scanner for reading user input */
    private final Scanner scanner = new Scanner(System.in);
    /** List of all bank accounts in the system */
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    /** Currently logged-in account */
    private BankAccount currentAccount;

    /**
     * Starts the banking application and manages the main program flow.
     * Handles user authentication and routes to appropriate account-specific menus.
     * Continues running until user chooses to exit.
     */
    public void start() {
        boolean running = true;

        while (running) {
            boolean isSignedIn = false;
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
                } else {
                    staySignedIn = displaySavingsAccountMenu();
                }
            }
        }
    }

    /**
     * Displays the authentication menu options for users.
     * Shows options for sign in, account creation, and exit.
     */
    private void displayAuthMenu() {
        System.out.println("\n[1] Sign in");
        System.out.println("[2] Create Account");
        System.out.println("[3] Exit");
    }

    /**
     * Displays the account creation menu and handles account type selection.
     * Routes to appropriate account creation based on user selection.
     */
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

    /**
     * Handles the user sign-in process.
     * Validates account numbers and authenticates users.
     *
     * @return true if sign-in is successful, false if user chooses to go back
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
     *
     * @param accountNo The account number to search for
     * @return true if account is found, false otherwise
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
     * Displays the savings account menu and available operations.
     *
     * @return true if user wants to stay signed in, false if signing out
     */
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

    /**
     * Handles savings account menu option selection and execution.
     *
     * @return true if user wants to stay signed in, false if signing out
     */
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

    /**
     * Displays the checking account menu and available operations.
     *
     * @return true if user wants to stay signed in, false if signing out
     */
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

    /**
     * Displays the credit card account menu and available operations.
     *
     * @return true if user wants to stay signed in, false if signing out
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
        System.out.println("[5] Display Information");
        System.out.println("[6] Close Account");
        System.out.println("[7] Sign out");

        return selectCCAccOptions();
    }

    /**
     * Handles checking account menu option selection and execution.
     *
     * @return true if user wants to stay signed in, false if signing out
     */
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

    /**
     * Handles credit card account menu option selection and execution.
     *
     * @return true if user wants to stay signed in, false if signing out
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
     * Handles investment account menu option selection and execution.
     *
     * @return true if user wants to stay signed in, false if signing out
     */
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

    /**
     * Displays the investment account menu and available operations.
     *
     * @return true if user wants to stay signed in, false if signing out
     */
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

    /**
     * Displays the available account types for account creation.
     * Shows options for Savings, Checking, Credit Card, and Investment accounts.
     */
    private void showAccountCreationOptions() {
        System.out.println("\n=====================");
        System.out.println("\nSELECT ACCOUNT TYPE");

        System.out.println("\n[1] Savings Account");
        System.out.println("[2] Checking Account");
        System.out.println("[3] Credit Card Account");
        System.out.println("[4] Investment Account");
        System.out.println("[5] Back to Main Menu");
    }

    /**
     * Prompts for and collects user's full name.
     *
     * @return The formatted full name (firstName + " " + lastName)
     */
    private String inputName() {
        System.out.print("\nFirst Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        return String.format("%s %s", firstName, lastName);
    }

    /**
     * Prompts for and validates account number input.
     * Ensures the account number is exactly 9 digits.
     *
     * @return A valid 9-digit account number
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
     * Creates a new account of the specified type.
     * Collects necessary information and initializes the account with appropriate default values.
     *
     * @param type The type of account to create ("Savings", "Checking", "Credit", or "Investment")
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
            case "Savings" -> bankAccounts.add(new BankAccount(accountNo, name));
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
     * Displays a prompt to press any key to return to the previous menu.
     */
    private void goBack() {
        System.out.println("\nEnter any key to go back");
        scanner.nextLine();
    }

    /**
     * Displays the current balance for the active account.
     */
    private void balanceInquiry() {
        System.out.println("\n======================");
        System.out.println("\nBALANCE INQUIRY");

        System.out.println("Your balance is: ₱" + currentAccount.inquireBalance());
    }

    /**
     * Displays the current investment value including earned interest
     * for investment accounts.
     */
    private void inquireInvestmentValue() {
        System.out.println("\n======================");
        System.out.println("\nINQUIRE INVESTMENT VALUE");

        double investmentValue = ((InvestmentAccount) currentAccount).inquireInvestmentValue();
        System.out.println("Your investment value is: ₱" + investmentValue);
    }

    /**
     * Handles menu option selection with input validation.
     *
     * @param max The maximum valid option number
     * @return The selected menu option (between 1 and max)
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
     * Gets and validates integer input from the user.
     * Ensures input is a valid non-negative integer.
     *
     * @param message The prompt message to display
     * @return The validated integer input
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
     * Gets and validates decimal input from the user.
     * Ensures input is a valid non-negative decimal number.
     *
     * @param message The prompt message to display
     * @return The validated decimal input
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
     * Main entry point of the banking application.
     * Creates and starts a new instance of the bank controller.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.start();
    }
}
