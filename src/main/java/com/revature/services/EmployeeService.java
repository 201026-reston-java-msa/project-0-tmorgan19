package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.AccountDao;
import com.revature.repositories.AccountDaoImpl;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerDaoImpl;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.repositories.EmployeeDao;

public class EmployeeService {

    CustomerDAO custRepository = null;
    AccountDao accRepository = null;
    EmployeeDao empRepository = null;
    public boolean loop = true;
    
    public EmployeeService(){
        custRepository = new CustomerDaoImpl();
        accRepository = new AccountDaoImpl();
        empRepository = new EmployeeDAOImpl();
    }

    public void EmployeeMenu(){
        System.out.println("Welcome to your employee home page. What would you like to do?");
        System.out.println("1. View all customers");
        System.out.println("2. View all bank accounts");
        System.out.println("3. Approve account applications");
        System.out.println("4. Log out");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice>4){
            System.out.println("Please input a valid number");
            EmployeeMenu();
        }
        switch (choice){
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
            case 4:
                this.loop = false;
                break;
        }
    }


    public void viewAllCustomers() {
        List<Customer> allCust = custRepository.findAll();
        if (allCust.isEmpty()){
            System.out.println("No customer accounts created yet.");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.printf("%10s %10s %10s %10s", "CUSTOMER ID", "FIRST NAME", "LAST NAME", "ACCOUNTS");
        System.out.print("\n");
        
        for (Customer c : allCust){
            int num = accRepository.findByCustId(c.getCustId()).size();
            String st1 = String.format("%10d %10s %10s %10d", c.getCustId(), c.getFirstName(), c.getLastName(), num);
            System.out.println(st1);
        }
        System.out.println("----------------------------------------------");
    }

    public void viewAllAccounts(){
        List<Account> accounts = accRepository.findAll();
        if (accounts.isEmpty()){
            System.out.println("No inactive accounts!");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.printf("%10s %10s %10s %10s", "ACCOUNT ID", "TYPE", "BALANCE", "ACTIVE");
        System.out.print("\n");
        for (Account a : accounts){
            String st1 = String.format("%10d %10s %10.2f %10s", a.getAccId(), a.getType(), a.getBalance(), a.getActivation());
            System.out.println(st1);
        }
        System.out.println("----------------------------------------------");
    }

    public void viewInactiveAccounts(){
        List<Account> accounts = accRepository.findAll();
        if (accounts.isEmpty()){
            System.out.println("No inactive accounts!");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.printf("%10s %10s %10s %10s", "ACCOUNT ID", "TYPE", "BALANCE", "ACTIVE");
        System.out.print("\n");
        for (Account a : accounts){
            if (!a.getActivation()){
                String st1 = String.format("%10d %10s %10.2f %10s", a.getAccId(), a.getType(), a.getBalance(), a.getActivation());
                System.out.println(st1);
            }
        }
        System.out.println("----------------------------------------------");
    }
    
    void approveAccount(int accId) {
        Account a = accRepository.findByAccId(accId);
        a.setActivation(true);
        accRepository.update(a);
    }

    public Employee employeeLogin(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please input your username:");
        String uname = input.nextLine();
        System.out.println("Please input your password:");
        String pword = input.nextLine();
        Employee e = login(uname, pword);
        return e;
    }
    
    public Employee login(String uname, String pword) {
		Employee eInDb = empRepository.findByUname(uname);
		try {
			if (uname.equalsIgnoreCase(eInDb.getUsername())) {
				System.out.println("That username exists -- now checking password");
				if (pword.equals(eInDb.getPassword())){
					System.out.println("Credentials verified - logged into system");
				}
				else {
					System.out.println("Password does not match username. Please start from login menu again\n");
					employeeLogin();
				}
			}
			else {
				System.out.println("That username does not exist");
				employeeLogin();
			}
		} catch (NullPointerException e){
			System.out.println("Username is not registered with us.");
			employeeLogin();
		}
		return eInDb;
	}
}
