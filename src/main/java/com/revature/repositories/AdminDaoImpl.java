package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Admin;
import com.revature.util.ConnectionUtil;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin findByUname(String Uname) {
        Admin a = null;
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from bank.admins where uname = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Uname);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
				int id = rs.getInt("adid");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String uname = rs.getString("uname");
				String pword = rs.getString("pword");
				a = new Admin(firstName, lastName, uname, pword);
				a.setAdId(id);
			}
		} catch (SQLException exc){
			System.out.println("Unable to find by username");
			exc.printStackTrace();
		}
		return a;
    }
    
}
