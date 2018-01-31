package com.school.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/nhn_db";
	static final String USERNAME = "nhn";
	static final String PASSWORD = "nhn123";
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;

	public void init() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("\n- MySQL Connection");
			st = conn.createStatement();
		} catch (SQLException se) {
			System.out.println("SQLException: " + se.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("DB ClassNotFoundException: " + e.getMessage());
		}
	}

	public void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (st != null) {
				st.close();
			}

		} catch (SQLException se) {
			System.out.println("SQLException: " + se.getMessage());
		}
	}

	public void excute(String sql) {
		try {
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String subjectName = rs.getString("subjectName");

				System.out.print("\n** subjectName : " + subjectName);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DbConnection db = new DbConnection();
		db.init();
		db.excute("select * from subject");
	}
}
