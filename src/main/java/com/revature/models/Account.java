package com.revature.models;

public class Account {

	private int id;
	private double balance;
	private String type;
	private boolean status = false;
	
	public Account(int id, double balance, String type, boolean status) {
		super();
		this.id = id;
		this.balance = balance;
		this.type = type;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
}
