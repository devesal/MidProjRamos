package main;

import models.BankAccount;
import models.CheckingAccount;
import models.CreditCardAccount;
import models.InvestmentAccount;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class BankController {

    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
    public ArrayList<InvestmentAccount> investmentAccounts = new ArrayList<>();
    public ArrayList<CreditCardAccount> creditCardAccounts = new ArrayList<>();

    public void start() {
        boolean running = true;

        do {
            System.out.println("\n=======================");
            System.out.println("WELCOME TO CO-PALS BANK");
            System.out.println("=======================");

            showArrayMenu();

            switch (selectMenuOption(8)) {
                case 1 -> accountCreationMenu();
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

    private void createAccount(String type) {
        System.out.println("\n=======================");
        System.out.println("\nCREATING ACCOUNT");

        System.out.print("\nLast Name: ");
        String lastName = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        String fullName = String.format("%s %s", lastName, firstName);

        switch (type) {
            case "Checking" -> {
                CheckingAccount account = new CheckingAccount(fullName);
                checkingAccounts.add(account);
            }
            case "Credit" -> {
                CreditCardAccount account = new CreditCardAccount(fullName);
                creditCardAccounts.add(account);
            }
            case "Investment" -> {
                InvestmentAccount account = new InvestmentAccount(fullName);
                investmentAccounts.add(account);
            }
        }

        System.out.println("\nAccount Created!");
    }

    private void accountCreationMenu() {
        showAccountCreationMenu();

        boolean backToMainMenu = false;
        switch (selectMenuOption(4)) {
            case 1 -> createAccount("Checking");
            case 2 -> createAccount("Credit");
            case 3 -> createAccount("Investment");
            case 4 -> backToMainMenu = true;
        }
    }

    private void showAccountCreationMenu() {
        System.out.println("\n=====================");
        System.out.println("\nSELECT ACCOUNT TYPE");

        System.out.println("\n[1] Checking Account");
        System.out.println("[2] Credit Card Account");
        System.out.println("[3] Investment Account");
        System.out.println("[4] Back to Main Menu");
    }

    private void balanceInquiry() {

    }

    private void deposit() {
    }

    private void withdraw() {
    }

    private void transferMoney() {

    }

    private void displayAccount() {

    }

    private void closeAccount() {
    }

    private void showArrayMenu() {
        System.out.println("\n[1] Create Account");
        System.out.println("[2] Balance Inquiry");
        System.out.println("[3] Deposit Transaction");
        System.out.println("[4] Withdraw Transaction");
        System.out.println("[5] Transfer Money");
        System.out.println("[6] Display Account Information");
        System.out.println("[7] Close Account");
        System.out.println("[8] Exit");
    }

    private int selectMenuOption(int max) {
        int input;
        while (true) {
            try {
                System.out.println("\n=======================");
                input = getIntInput("\nSELECT OPTION: ");
                if (input >= 1 && input <= max) {
                    return input;
                }

                System.out.println("Invalid option. Please enter a number between 1 and " + max + ".");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private int getIntInput(String s) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);

        return Integer.parseInt(scanner.nextLine());
    }

    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.start();
    }
}
