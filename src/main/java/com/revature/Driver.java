package com.revature;

import java.util.Scanner;

import com.revature.models.Admin;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.services.AccountService;
import com.revature.services.AdminService;
import com.revature.services.CustomerService;
import com.revature.services.EmployeeService;

public class Driver {
	static boolean cont = true;

	public static void main(String[] args) {
		
		System.out.println("Welcome to the banking application. \nPlease select how you would like to log in.");
		while (cont){
			loginPage();
		}
	}

	public static void loginPage(){
		System.out.println("Are you logging in as a");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Admin");
		System.out.println("4. Exit program");
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
				Employee e;
				e = es.employeeLogin();
				while (es.loop){
					es.EmployeeMenu();
				}
				break;
			case 3:
				AdminService ads = new AdminService();
				Admin a;
				a = ads.adminLogin();
				while (ads.loop){
					ads.AdminMenu();
				}
				break;
			case 4:
				cont = false;
				break;
		}
	}
}
