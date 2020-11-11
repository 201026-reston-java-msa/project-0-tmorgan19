package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDao {

    @Override
    public Employee findByUname(String Uname) {
        Employee e = null;
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from bank.employees where uname = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Uname);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
				int id = rs.getInt("empid");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String uname = rs.getString("uname");
				String pword = rs.getString("pword");
				e = new Employee(firstName, lastName, uname, pword);
				e.setEmpId(id);
			}
		} catch (SQLException exc){
			System.out.println("Unable to find by username");
			exc.printStackTrace();
		}
		return e;
    }
    
}
