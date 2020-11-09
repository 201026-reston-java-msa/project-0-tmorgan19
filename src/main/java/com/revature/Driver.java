package com.revature;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class Driver {

	public static void main(String[] args) {
		
		CustomerService cs = new CustomerService();
		Customer c;
		
		System.out.println("Welcome to the banking application. \nPlease choose from the following options: \n");
		c = cs.loginMenu();
		System.out.println(c.toString());
		

	}

	// static Customer loginMenu() {
	// 	CustomerService cs = new CustomerService();
	// 	Customer c = null;
	// 	System.out.println("1. Login to existing user account");
	// 	System.out.println("2. Register a new user account");
		
	// 	Scanner input = new Scanner(System.in);
	// 	/*
	// 	 * NEED TO ADD INPUT VALIDATION for non-integers
	// 	 */
	// 	int choice = input.nextInt();
	// 	if (choice!=1 && choice!=2) {
	// 		System.out.println("Please input either a 1 or 2 for the options displayed");
	// 		loginMenu();
	// 	}
	// 	switch (choice) {
	// 		case 1: 
	// 			System.out.println("Please input your username:");
	// 			String uname = input.nextLine();
	// 			System.out.println("Please input your password");
	// 			String pword = input.nextLine();
	// 			cs.login(uname, pword);
	// 			break;
	// 		case 2:
	// 			c = cs.registerNewCustomer();
	// 			break;
	// 		}
	// 	return c;
	// }
}
