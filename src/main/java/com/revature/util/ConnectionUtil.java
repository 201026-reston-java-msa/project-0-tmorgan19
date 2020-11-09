package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	//Add logging
	
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("C:\\Users\\tommy\\Coding\\Java Training\\project-0-tmorgan19\\src\\main\\resources\\db.properties"));
		
			conn = DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("username"), 
					prop.getProperty("password")
					);
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
		} catch (FileNotFoundException e) {
			System.out.println("Could not find database properties file");
		} catch (IOException e) {
			System.out.println("Could not connect to database");
		}
		
		return conn;
	}
}
