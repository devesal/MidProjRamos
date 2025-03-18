package main;

import models.BankAccount;
import models.CheckingAccount;
import models.CreditCardAccount;
import models.InvestmentAccount;

import java.util.ArrayList;
import java.util.Scanner;

public class BankController {

    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<CheckingAccount> checkingAccounts;
    public ArrayList<InvestmentAccount> investmentAccounts;
    public ArrayList<CreditCardAccount> creditCardAccounts;

    public void start() {
        boolean running = true;

        do {
            System.out.println("\nWELCOME TO CO-PALS BANK");

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
        System.out.println("Name: ");
        String name = scanner.nextLine();

        switch (type) {
            case "Checking" -> {
                CheckingAccount account = new CheckingAccount(name);
                checkingAccounts.add(account);
            }
            case "Credit" -> {
                CreditCardAccount account = new CreditCardAccount(name);
                creditCardAccounts.add(account);
            }
            case "Investment" -> {
                InvestmentAccount account = new InvestmentAccount(name);
                investmentAccounts.add(account);
            }
        }
    }


    private void accountCreationMenu() {
        boolean backToMainMenu = false;
        do {
            showAccountCreationMenu();

            switch (selectMenuOption(4)) {
                case 1 -> createCheckingAccount();
                case 2 -> createCreditCardAccount();
                case 3 -> createInvestmentAccount();
                case 4 -> backToMainMenu = true;
            }
        } while (!backToMainMenu);
    }

    private void createInvestmentAccount() {
        InvestmentAccount newAccount = new InvestmentAccount();
        newAccount.setAccountName(scanner.nextLine());

        investmentAccounts.add(newAccount);
    }

    private void createCreditCardAccount() {
        System.out.println("Name: ");
        CreditCardAccount newAccount = new CreditCardAccount();
        newAccount.setAccountName(scanner.nextLine());

        creditCardAccounts.add(newAccount);
    }

    private void createCheckingAccount() {
        System.out.print("Name: ");
        CheckingAccount newAccount = new CheckingAccount();
        newAccount.setAccountName(scanner.nextLine());

        checkingAccounts.add(newAccount);
    }

    private void showAccountCreationMenu() {
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
        do {
            input = getIntInput("\nSELECT OPTION: ");
            if (input < 1 || input > max) {
                System.out.println("Invalid option");
            }
        } while (input < 1 || input > max);

        return input;
    }

    private int getIntInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);

        return Integer.parseInt(scanner.nextLine());
    }

    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.start();
    }
}
