package com.revature.repositories;

import com.revature.models.Employee;

public interface EmployeeDao {
    public Employee findByUname(String Uname);
}
