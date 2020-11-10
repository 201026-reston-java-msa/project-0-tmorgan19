package com.revature;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;
import com.revature.services.EmployeeService;

public class Driver {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the banking application. \nPlease choose from the following options: \n");
		System.out.println("Are you logging in as a");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Admin");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch (choice){
			case 1:
				CustomerService cs = new CustomerService();
				Customer c;
				c = cs.loginMenu();
				System.out.println(c.toString());
				AccountService as = new AccountService(c.getCustId());
				while (as.loop){
					as.accountMenu();
				}
				break;
			case 2:
				EmployeeService es = new EmployeeService();
				/**
				 * Need to add login verification
				 */
				while (es.loop){
					es.EmployeeMenu();
				}
				break;
			case 3:

		}

	}

}
