package com.revature.services;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerDaoImpl;

public class CustomerService {

	CustomerDAO repository = null;
	
	public CustomerService() {
		repository = new CustomerDaoImpl();
	}
	
	public boolean login(String uname, String pword) {
		Customer cIndDb = repository.findByUname(uname);
		try {
			if (uname.equalsIgnoreCase(cIndDb.getUsername())) {
				System.out.println("That username exists");
				if (pword.equals(cIndDb.getPassword())){
					System.out.println("Credentials verified - logged into system");
				}
			}
			else {
				System.out.println("That username does not exist");
			}
		} catch (NullPointerException e){
			System.out.println("Username is not registered with us.");

		}

		return false;
	}
	
	/*
	 * Add input validations
	 */
	public Customer registerNewCustomer() {
		Scanner input = new Scanner(System.in);
		System.out.println("Thank you for choosing to register with us.\n"
				+ "Please input your first name.");
		String fname = input.nextLine();
		System.out.println("Please input your last name.");
		String lname = input.nextLine();
		System.out.println("Please input a username to login with.");
		String uname = input.nextLine();
		System.out.println("Please input a password to login with.");
		String pword = input.nextLine();
		
		Customer c = new Customer(fname, lname, uname, pword);
		System.out.println("Success! New user account created");
		System.out.println(c.toString());
		
		if (!addCustomer(c)) {
			System.out.println("Database customer insertion unsuccessful");
		} else {
			System.out.println("Successfully added to database");
		}
		return c;
	}
	
	public boolean addCustomer(Customer c) {
		return repository.insert(c);
	}
}
