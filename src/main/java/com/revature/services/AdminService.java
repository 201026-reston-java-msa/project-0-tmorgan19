package com.revature.services;

import java.util.Scanner;

public class AdminService extends EmployeeService {
    
    public AdminService(){
        super();
    }
    public void AdminMenu(){
        System.out.println("Welcome to your admin home page. What would you like to do?");
        System.out.println("1. View all customers");
        System.out.println("2. View all bank accounts");
        System.out.println("3. Approve account applications");
        System.out.println("4. Make a deposit");
        System.out.println("5. Make a withdrawl");
        System.out.println("6. Make a transfer");
        System.out.println("7. Log out");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice>7){
            System.out.println("Please input a valid number");
            AdminMenu();
        }
        switch(choice){
            case 1:
                viewAllCustomers();
                break;
            case 2:
                viewAllAccounts();
                break;
            case 3:
                viewInactiveAccounts();
                System.out.println("Which inactive accounts would you like to activate? Please specify a single account ID");
                int accId = input.nextInt();
                approveAccount(accId);
                break;
            case 4: //deposit
                AccountService as = new AccountService();
                viewAllAccounts();
                System.out.println("Please select the account you would like to deposit into by typing its Account ID");
                int accId2 = input.nextInt();
                System.out.println("How much would you like to deposit?");
                double amount = input.nextDouble();
                as.deposit(accId2, amount);
                break;
            case 5: //withdraw
                AccountService as2 = new AccountService();
                viewAllAccounts();
                System.out.println("Please select the account you would like to withdraw from by typing its account id");
                int accId3 = input.nextInt();
                System.out.println("How much would you like to withdraw?");
                double amount2 = input.nextDouble();
                as2.withdraw(accId3, amount2);
                break;
            case 6: //transfer
                AccountService as3 = new AccountService();
                viewAllAccounts();
                System.out.println("Which account would you like to take money from? Type the Account ID");
                int fromAcc = input.nextInt();
                System.out.println("Which account would you like to transfer money to? Type the Account ID");
                int toAcc = input.nextInt();
                System.out.println("How much money would you like to transfer?");
                double amount3 = input.nextDouble();
                as3.transfer(fromAcc, toAcc, amount3);
                break;
            case 7:
                this.loop = false;
                break;
        }
    }
    
}
