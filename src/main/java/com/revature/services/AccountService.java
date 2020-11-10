package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.repositories.AccountDao;
import com.revature.repositories.AccountDaoImpl;

public class AccountService {
    AccountDao repository = null;
    int custId;
    public boolean loop = true;

    public AccountService(){
        repository = new AccountDaoImpl();
    }
    public AccountService(int custId){
        repository = new AccountDaoImpl();
        this.custId = custId;
    }

    public void registerNewAccount(){
        String type = null;
        System.out.println("Thanks for applying to open a new account with us. Would you like to open a savings or checking account?");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice==1) {
            type = "Savings";
        } else if (choice==2){
            type = "Checking";
        } else{
            System.out.println("Please input a valid number option.");
            registerNewAccount();
        }
        Account a = new Account(this.custId, type);
        if (addAccount(a)){
            System.out.println("Account created and in the system! Note: Account cannot be used until approved by an employee");
        }
        else{
            System.out.println("Account was not able to be registered");
        }
    }

    public boolean addAccount(Account a){
        return repository.insert(a);
    }
    public void accountMenu(){
		System.out.println("Welcome to your user homepage. What would you like to do?");
		System.out.println("1. View all my bank accounts");
		System.out.println("2. Make a withdrawl");
		System.out.println("3. Make a deposit");
		System.out.println("4. Transfer funds between accounts");
		System.out.println("5. Apply to open a new account");
		System.out.println("6. Log out of my user account");
		Scanner input = new Scanner(System.in);

		int choice = input.nextInt();
		if (choice>6) {
			System.out.println("Please input one of the valid options");
			accountMenu();
		}
		switch (choice){
            case 1: //view accounts
                viewMyAccounts();
                break;
            case 2: //withdraw
                if (this.hasActiveAccount()){
                    viewMyAccounts();
                    System.out.println("Please select the account you would like to deposit into by typing its account id");
                    int accId = input.nextInt();
                    System.out.println("How much would you like to withdraw?");
                    double amount = input.nextDouble();
                    withdraw(accId, amount);
                }
                else {
                    System.out.println("Cannot perform this action since you do not have any activated accounts");
                    this.accountMenu();
                }
                break;
            case 3: //deposit
                if (this.hasActiveAccount()){
                    viewMyAccounts();
                    System.out.println("Please select the account you would like to deposit into by typing its Account ID");
                    int accId = input.nextInt();
                    System.out.println("How much would you like to deposit?");
                    double amount = input.nextDouble();
                    deposit(accId, amount);
                }
                else {
                    System.out.println("Cannot perform this action since you do not have any activated accounts");
                    this.accountMenu();
                }
                    break;
            case 4: //transfer
                if (this.hasActiveAccount()){
                    viewMyAccounts();
                    System.out.println("Which account would you like to take money from? Type the Account ID");
                    int fromAcc = input.nextInt();
                    System.out.println("Which account would you like to transfer money to? Type the Account ID");
                    int toAcc = input.nextInt();
                    System.out.println("How much money would you like to transfer?");
                    double amount = input.nextDouble();
                    transfer(fromAcc, toAcc, amount);

                }
                else {
                    System.out.println("Cannot perform this action since you do not have any activated accounts");
                    this.accountMenu();
                }
                break;
			case 5: //new account
                registerNewAccount();
				break;
            case 6: //log out
                this.loop = false;
                break;
		}
    }
    
    public void viewMyAccounts() {
        List<Account> myAccounts = repository.findByCustId(custId);
        if (myAccounts.isEmpty()){
            System.out.println("No open accounts. Feel free to open one today!");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.printf("%10s %10s %10s %10s", "ACCOUNT ID", "TYPE", "BALANCE", "ACTIVE");
        System.out.print("\n");
        
        for (Account a : myAccounts){
            String st1 = String.format("%10d %10s %10.2f %10s", a.getAccId(), a.getType(), a.getBalance(), a.getActivation());
            System.out.println(st1);
        }
        System.out.println("----------------------------------------------");
    }

    public boolean hasActiveAccount(){
        boolean value = false;
        List<Account> myAccounts = repository.findByCustId(custId);
        for (Account a : myAccounts){
            if (a.getActivation()){
                value = true;
            }
        }
        return value;
    }

    public void deposit(int accId, double amount){
        Account a = repository.findByAccId(accId);
        double newBal = a.getBalance() + amount;
        a.setBalance(newBal);
        repository.update(a);
    }

    private void withdraw(int accId, double amount) {
        Account a = repository.findByAccId(accId);
        double newBal = a.getBalance() - amount;
        if (newBal > 0){
            a.setBalance(newBal);
            repository.update(a);
        } else {
            System.out.println("Insufficient funds to withdraw that amount.");
        }

    }

    private void transfer(int fromAcc, int toAcc, double amount) {
        withdraw(fromAcc, amount);
        deposit(toAcc, amount);
    }
}
