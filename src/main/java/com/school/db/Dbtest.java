package com.school.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.school.models.vo.Subject;

public class Dbtest {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				//				Properties prop = new Properties();
				//				InputStream inputStream = DbConnection.class.getClassLoader().getResourceAsStream("/db.properties");
				//				prop.load(inputStream);
				//				String driver = prop.getProperty("driver");
				//				String url = prop.getProperty("url");
				//				String user = prop.getProperty("user");
				//				String password = prop.getProperty("password");

				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nhn_db", "nhn", "nhn123");
				System.out.println("\n- MySQL Connection");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}

	}

	public static void main(String[] args) throws SQLException {
		Connection conn = Dbtest.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from subject");
		List<Subject> subList = new ArrayList<Subject>();

		while (rs.next()) {
			Subject sub = new Subject();
			sub.setSub_Id(rs.getInt("sub_Id"));
			sub.setSubjectName(rs.getString("subjectName"));
			subList.add(sub);
		}

		System.out.println(subList);
	}
}
