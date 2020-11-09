package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {

    @Override
    public List<Account> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Account> findByCustId(int id) {
        List<Account> customerAccounts = new ArrayList<Account>();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from bank.accounts where custId = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                int accId = rs.getInt("accId");
                int custId = rs.getInt("custId");
                double balance = rs.getDouble("balance");
                String type = rs.getString("accType");
                boolean activation = rs.getBoolean("activation");

                Account a = new Account(accId, custId, balance, type, activation);
                customerAccounts.add(a);
            }
            rs.close();
        } catch (SQLException e){
            System.out.println("Could not find list of accounts by customer ID");
        }
        return customerAccounts;
    }

    @Override
    public Account findByAccId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(Account a) {
        try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank.accounts (custId, balance, accType, activation)"
					+ " VALUES (?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a.getCustId());
			stmt.setDouble(2, a.getBalance());
			stmt.setString(3, a.getType());
			stmt.setBoolean(4, a.getActivation());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Unable to insert customer into table");
			e.printStackTrace();
		}
		return true;
    }

    @Override
    public boolean update(Account a) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
