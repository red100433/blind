package com.school.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		PreparedStatement preparedStatement = connection
			.prepareStatement(
				"select s.sub_Id, s.subjectName, avg(grade) as avg from grade g inner join subject s on g.sub_Id = s.sub_Id group by sub_Id");
		List<String> resultList = new ArrayList<>();
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String result1 = rs.getString("sub_Id");
			String result2 = rs.getString("subjectName");
			String result3 = rs.getString("avg");
			String result = "sub_Id:" + result1 + " subject:" + result2 + " avg:" + result3;
			resultList.add(result);
		}
		System.out.println(resultList);
	}
}
