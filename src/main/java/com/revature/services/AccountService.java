package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.repositories.AccountDao;
import com.revature.repositories.AccountDaoImpl;

public class AccountService {
    AccountDao repository = null;
    int custId;

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
            case 1:
                viewMyAccounts();
                break;
			case 2:
			case 3:
			case 4:
			case 5:
                registerNewAccount();
				break;
			case 6:
		}
    }
    
    public void viewMyAccounts(){
        List<Account> myAccounts = repository.findByCustId(custId);
        System.out.println("----------------------------------------------");
        System.out.printf("%10s %10s %10s %10s", "ACCOUNT ID", "TYPE", "BALANCE", "ACTIVE");
        System.out.print("\n");
        for (Account a : myAccounts){
            // System.out.printf("%d", a.getAccId());
            // System.out.printf("%s", a.getType());
            // System.out.printf("%f", a.getBalance());
            // System.out.printf("%s", a.getActivation());
            String st1 = String.format("%10d %10s %10.2f %10s", a.getAccId(), a.getType(), a.getBalance(), a.getActivation());
            System.out.println(st1);
        }
        System.out.println("----------------------------------------------");
    }
}
