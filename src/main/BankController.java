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
        String pin = setupPin();

        int accountNo = 0;
        switch (type) {
            case "Checking" -> {
                CheckingAccount account = new CheckingAccount(fullName, pin);
                checkingAccounts.add(account);
                System.out.println(account.getPin());
                accountNo = account.getAccountNo();
            }
            case "Credit" -> {
                CreditCardAccount account = new CreditCardAccount(fullName, pin);
                creditCardAccounts.add(account);
                accountNo = account.getAccountNo();
            }
            case "Investment" -> {
                InvestmentAccount account = new InvestmentAccount(fullName, pin);
                investmentAccounts.add(account);
                accountNo = account.getAccountNo();
            }
        }

        System.out.println("\nAccount Created!");
        System.out.println(fullName);
        System.out.println(accountNo);
    }

    private String setupPin() {
        String pin1;
        String pin2;

        while (true) {
            pin1 = inputPin("PIN: ");

            pin2 = inputPin("Confirm your PIN: ");

            if (!pin1.equals(pin2)) {
                System.out.println("PIN does not match.");
                continue;
            }

            return pin1;
        }
    }

    private String inputPin(String s) {
        String pin;
        do {
            System.out.print(s);
            pin = scanner.nextLine();

            if (pin.length() != 6) {
                System.out.println("PIN must be 6 digits.");
            }
        } while (pin.length() != 6);

        return pin;
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
        System.out.println("\n======================");
        System.out.println("\nBALANCE INQUIRY");

        System.out.print("\nAccount Number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());

        String pin = inputPin("PIN: ");

        for (CheckingAccount account : checkingAccounts) {
            if (account.getAccountNo() == accountNumber) {
                if (account.getPin().equals(pin)) {
                    System.out.println(account);
                } else {
                    System.out.println("Incorrect PIN.");
                }
                return;
            }
        }

        System.out.println("Account not found.");
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
