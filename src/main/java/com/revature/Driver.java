package com.revature;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;

public class Driver {

	public static void main(String[] args) {
		
		CustomerService cs = new CustomerService();
		
		Customer c;
		
		System.out.println("Welcome to the banking application. \nPlease choose from the following options: \n");
		c = cs.loginMenu();
		System.out.println(c.toString());

		AccountService as = new AccountService(c.getCustId());
		while (as.loop){
			as.accountMenu();
		}
		


	}

}
