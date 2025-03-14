package main;

import models.BankAccount;
import models.CheckingAccount;
import models.CreditCardAccount;
import models.InvestmentAccount;

import java.util.Scanner;

public class BankController {
    public CheckingAccount[] checkingAccounts = new CheckingAccount[10];
    public CreditCardAccount[] creditCardAccounts = new CreditCardAccount[10];
    public InvestmentAccount[] investmentAccounts = new InvestmentAccount[10];

    public static void main(String[] args) {
        boolean running = true;

        do {
            System.out.println("\nWELCOME TO CO-PALS BANK");

            showArrayMenu();

            switch (selectMenuOption(8)) {
                case 1 -> createAccount();
                case 2 -> balanceInquiry();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> transferMoney();
                case 6 -> displayAccount();
                case 7 -> closeAccount();
                case 8 -> running = false;
            }
        } while (running);
    }

    private static void createAccount() {
        boolean backToMainMenu = false;
        do {
            showAccountCreationMenu();

            switch (selectMenuOption(4)) {
                case 1 -> createAccount("Checking");
                case 2 -> createAccount("CreditCard");
                case 3 -> createAccount("Investment");
                case 4 -> backToMainMenu = true;
            }
        } while (!backToMainMenu);
    }

    private static void createAccount(String type) {
        System.out.println("\nCHECKING ACCOUNT CREATION");

        String firstName = getStringInput("First Name: ");
        String lastName = getStringInput("Last Name: ");
        String name = firstName + " " + lastName;

        BankAccount account = null;
        switch (type) {
            case "Checking" -> account = new CheckingAccount(name);
            case "CreditCard" -> account = new CreditCardAccount(name);
            case "Investment" -> account = new InvestmentAccount(name);
        }
        addAccount(account, type);
    }

    private static void addAccount(BankAccount account, String type) {
        // TODO
    }

    private static void showAccountCreationMenu() {
        System.out.println("\nSELECT ACCOUNT TYPE");

        System.out.println("\n1. Checking Account");
        System.out.println("2. Credit Card Account");
        System.out.println("3. Investment Account");
        System.out.println("4. Back to Main Menu");
    }

    private static void balanceInquiry() {
    }

    private static void deposit() {
    }

    private static void withdraw() {
    }

    private static void transferMoney() {

    }

    private static void displayAccount() {

    }

    private static void closeAccount() {
    }

    private static void showArrayMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Balance Inquiry");
        System.out.println("3. Deposit Transaction");
        System.out.println("4. Withdraw Transaction");
        System.out.println("5. Transfer Money");
        System.out.println("6. Display Account Information");
        System.out.println("7. Close Account");
        System.out.println("8. Exit");
    }

    private static int selectMenuOption(int max) {
        int input;
        do {
            input = getIntInput("\nSELECT OPTION: ");
            if (input < 1 || input > max) {
                System.out.println("Invalid option");
            }
        } while (input < 1 || input > max);

        return input;
    }

    private static int getIntInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);

        return Integer.parseInt(scanner.nextLine());
    }

    private static String getStringInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);

        return scanner.nextLine();
    }
}
