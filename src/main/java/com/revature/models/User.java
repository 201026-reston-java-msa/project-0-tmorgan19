package com.revature.models;

public abstract class User {
    String firstName;
	String lastName;
	String username;
    String password;

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    
}
