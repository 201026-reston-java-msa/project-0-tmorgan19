package com.revature.models;

public class Account {

	private int accId;
	private int custId;
	private double balance = 0;
	private String type;
	private boolean activation = false;
	
	public Account(int owner, String type) {
		this.custId = owner;
		this.type = type;
	}

	public Account(int accId, int custId, double balance, String type, boolean activation){
		this.accId = accId;
		this.custId = custId;
		this.balance = balance;
		this.type = type;
		this.activation = activation;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int id) {
		this.accId = id;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int id) {
		this.custId = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}
	
	
	
	
}
