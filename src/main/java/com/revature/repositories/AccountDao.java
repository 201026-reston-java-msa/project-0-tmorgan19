package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {

	public List<Account> findAll();
	public Account findById(int id);
	public boolean insert(Account a);
	public boolean update(Account a);
	
}
