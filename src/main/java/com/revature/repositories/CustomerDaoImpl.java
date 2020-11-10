package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDAO {

	@Override
	public List<Customer> findAll() {
		List<Customer> list = new ArrayList<Customer>();
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from bank.customers;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
				Customer c = null;
				int custId = rs.getInt("custid");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String uname = rs.getString("uname");
				String pword = rs.getString("pword");
				c = new Customer(firstName, lastName, uname, pword);
				c.setCustId(custId);
				list.add(c);
			}
		} catch (SQLException e){
			System.out.println("Unable to find by username");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Customer c) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank.customers (firstName, lastName, uname, pword)"
					+ " VALUES (?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getFirstName());
			stmt.setString(2, c.getLastName());
			stmt.setString(3, c.getUsername());
			stmt.setString(4, c.getPassword());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Unable to insert customer into table");
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer findByUname(String Uname) {
		Customer c = null;
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from bank.customers where uname = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Uname);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
				int custId = rs.getInt("custid");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String uname = rs.getString("uname");
				String pword = rs.getString("pword");
				c = new Customer(firstName, lastName, uname, pword);
				c.setCustId(custId);
			}
		} catch (SQLException e){
			System.out.println("Unable to find by username");
			e.printStackTrace();
		}
		return c;
	}
}
