/**
 * The BankController class manages user authentication, account creation,
 * and various banking operations, including deposits, withdrawals, and transfers.
 *
 * Features:
 * - User Authentication (Sign-in, Account Creation)
 * - Account Management (Checking, Credit Card, Investment)
 * - Transaction Processing
 *
 * @author
 * @date
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
     * Starts the banking system and manages authentication and transactions.
     */
    public void start() {
        boolean running = true;
        boolean isLoggedIn = false;

        while (running) {

            // Authentication Loop
            while (!isLoggedIn) {
                System.out.println("\n=======================");
                System.out.println("WELCOME TO CO-PALS BANK");
                System.out.println("=======================");

                displayAuthMenu();

                switch (selectMenuOption(3)) {
                    case 1 -> {
                        signIn();
                        if (currentAccount != null) {
                            isLoggedIn = true;
                        }
                    }
                    case 2 -> displayAccCreationMenu();
                    case 3 -> {return;} // Exits program
                }
            }

            displayMainMenu();

            switch (selectMenuOption(8)) {
                case 1 -> balanceInquiry();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transferMoney();
                case 5 -> displayAccount();
                case 6 -> closeAccount();
                case 7 -> running = false;
            }
        }
    }
    /**
     * Handles user sign-in by verifying account number.
     */
    private void signIn() {
        System.out.println("\n=======================");
        System.out.println("\nSIGN IN");
        int accountNo = getIntInput("\nAccount Number: ");

        for (BankAccount account : bankAccounts) {
            if (account.getAccountNo() == accountNo) {
                currentAccount = account;
                System.out.println("Login successful!");
                // Proceed to account menu
                return;
            }
        }

        System.out.println("\nIncorrect account number");
        goBack();
    }
    /**
     * Displays authentication menu.
     */
    private void displayAuthMenu() {
        System.out.println("\n[1] Sign in");
        System.out.println("[2] Create Account");
        System.out.println("[3] Exit");
    }
    /**
     * Displays account creation menu.
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
     * Displays account type options.
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
     * Prompts user to enter their first and last name.
     *
     * @return Full name as a formatted string
     */
    private String inputName() {
        System.out.print("\nFirst Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Last Name: ");
        String firstName = scanner.nextLine();

        return String.format("%s %s", lastName, firstName);
    }
    /**
     * Ensures account number is unique and valid.
     *
     * @return Valid account number
     */
    private int inputAccountNo() { // SHOULD NOT ALLOW DUPLICATE ACCOUNT NUMBERS
        int input;
        while (true) {
            input = getIntInput("Account Number: ");

            if (String.valueOf(input).length() == 9) {
                return input;
            }

            System.out.println("\nAccount number must be 9 digits.");
        }
    }
    /**
     * Creates an account based on user selection.
     *
     * @param type The type of account to create
     */
    private void createAccount(String type) {
        System.out.println("\n=======================");
        System.out.println("\nCREATE ACCOUNT");

        String name = inputName();
        int accountNo = inputAccountNo();

        switch (type) {
            case "Checking" -> bankAccounts.add(new CheckingAccount(accountNo, name,  0.0));

            case "Credit" -> bankAccounts.add(new CreditCardAccount(accountNo, name, 0.0, 100000));

            case "Investment" -> bankAccounts.add(new InvestmentAccount(accountNo, name, 50000, 3.5));

            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        System.out.println("\nAccount Created!");
        System.out.println(name);
        System.out.println(accountNo);

        goBack();
    }
    /**
     * Prompts the user to press any key to return to the previous menu.
     */
    private void goBack() {
        System.out.println("\nEnter any key to go back");
        scanner.nextLine();
    }
    /**
     * Displays balance inquiry information.
     */
    private void balanceInquiry() {
        System.out.println("\n======================");
        System.out.println("\nBALANCE INQUIRY");

        System.out.println(currentAccount);
    }
    /**
     * Handles deposit transactions.
     */
    private void deposit() {
    }
    /**
     * Handles withdrawal transactions.
     */
    private void withdraw() {
    }
    /**
     * Handles money transfers between accounts.
     */
    private void transferMoney() {

    }
    /**
     * Displays detailed account information.
     */
    private void displayAccount() {

    }
    /**
     * Handles account closure operations.
     */
    private void closeAccount() {
    }
    /**
     * Displays the main menu options.
     */
    private void displayMainMenu() {
        System.out.println("\n=======================");
        System.out.println("\n#" + currentAccount.getAccountNo() + "\n");

        System.out.println("[1] Balance Inquiry");
        System.out.println("[2] Deposit Transaction");
        System.out.println("[3] Withdraw Transaction");
        System.out.println("[4] Transfer Money");
        System.out.println("[5] Display Account Information");
        System.out.println("[6] Close Account");
        System.out.println("[7] Exit");
    }
    /**
     * Prompts the user to select a menu option.
     *
     * @param max Maximum valid menu option
     * @return The selected menu option
     */
    private int selectMenuOption(int max) {
        int input;
        while (true) {
            System.out.println("\n=======================");
            input = getIntInput("\nSELECT OPTION: ");
            if (input >= 1 && input <= max) {
                return input;
            }

            System.out.println("Invalid option. Please enter a number between 1 and " + max + ".");
        }
    }
    /**
     * Gets integer input from the user.
     *
     * @param s Prompt message
     * @return Integer input
     */
    private int getIntInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);

        int input = 0;
        try {

            while (true) {
                input = Integer.parseInt(scanner.nextLine());

                if (input > 0) {
                    break;
                }
                System.out.println("Input must be a positive integer.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number");
        }

        return input;
    }

    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.start();
    }
}
