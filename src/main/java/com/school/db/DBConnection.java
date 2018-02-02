package com.school.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import lombok.extern.java.Log;

@Log
public class DBConnection {

	private static Connection connection = null;

	//TODO dbcp array connect pool
	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				Properties prop = new Properties();
				InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("/db.properties");
				prop.load(inputStream);
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("\n- MySQL Connection");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return connection;
		}

	}
}
