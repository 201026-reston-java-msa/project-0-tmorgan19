package com.revature.repositories;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDAO {

	public List<Customer> findAll();
	public Customer findByUname(String Uname);
	public boolean insert(Customer c);
}
