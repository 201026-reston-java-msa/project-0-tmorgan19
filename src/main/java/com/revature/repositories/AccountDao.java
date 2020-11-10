package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {

	public List<Account> findAll();
	public List<Account> findByCustId(int id);
	public Account findByAccId(int id);
	public boolean insert(Account a);
	public boolean updateBal(Account a);
	
}
