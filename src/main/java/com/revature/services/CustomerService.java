package com.revature.services;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerDaoImpl;

import org.apache.log4j.Logger;

public class CustomerService {
	static Logger log = Logger.getLogger(CustomerService.class);
	CustomerDAO repository = null;
	
	public CustomerService() {
		repository = new CustomerDaoImpl();
	}
	public Customer loginMenu(){
		{
			Customer c = null;
			System.out.println("\n1. Login to existing user account");
			System.out.println("2. Register a new user account");
			
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			input.nextLine();
			if (choice!=1 && choice!=2) {
				System.out.println("Please input either a 1 or 2 for the options displayed");
				loginMenu();
			}
			switch (choice) {
				case 1: 
					System.out.println("Please input your username:");
					String uname = input.nextLine();
					System.out.println("Please input your password:");
					String pword = input.nextLine();
					c = login(uname, pword);
					break;
				case 2:
					c = registerNewCustomer();
					break;
				}
			return c;
		}
	}
	
	public Customer login(String uname, String pword) {
		Customer cInDb = repository.findByUname(uname);
		try {
			if (uname.equalsIgnoreCase(cInDb.getUsername())) {
				System.out.println("That username exists -- now checking password");
				if (pword.equals(cInDb.getPassword())){
					System.out.println("Credentials verified - logged into system");
					log.info("Customer logged in");
				}
				else {
					System.out.println("Password does not match username. Please start from login menu again\n");
					log.info("Incorrect password attempt");
					loginMenu();
				}
			}
			else {
				System.out.println("That username does not exist");
				log.info("Incorrect username attempt");
				loginMenu();
			}
		} catch (NullPointerException e){
			System.out.println("Username is not registered with us.");
			log.info("Incorrect username attempt.");
			loginMenu();
		}
		return cInDb;
	}
	
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
		log.info("New customer account created");
		
		if (!addCustomer(c)) {
			System.out.println("Database customer insertion unsuccessful");
			log.warn("New customer account not added to database");
		} else {
			System.out.println("Successfully added to database");
			log.info("New customer account added to database");
			c = repository.findByUname(uname);
		}
		return c;
	}
	
	public boolean addCustomer(Customer c) {
		return repository.insert(c);
	}
}
