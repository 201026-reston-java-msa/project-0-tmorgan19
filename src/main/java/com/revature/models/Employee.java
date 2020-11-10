package com.revature.models;

public class Employee {
    
    private int empId;
    private String firstName;
	private String lastName;
	private String username;
	private String password;

    public Employee(String firstName, String lastName, String username, String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
