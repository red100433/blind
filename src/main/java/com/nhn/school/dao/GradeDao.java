package com.nhn.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nhn.school.dao.custom.GradeDaoWrapper;
import com.nhn.school.db.DBConnection;
import com.nhn.school.models.Type;
import com.nhn.school.models.vo.Grade;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeDao extends GradeDaoWrapper {
	private Connection connection;

	private static GradeDao t;

	public static GradeDao getInstance() {
		synchronized (GradeDao.class) {
			if (t == null) {
				t = new GradeDao();
			}
		}
		return t;
	}

	public GradeDao() {
		connection = DBConnection.getConnection();
	}

	@Override
	public List<String> getAllList(String selectOption) {
		List<String> resultList = new ArrayList<>();
		try {
			if (selectOption.equals(Type.ALL_SUBJECT_AVERAGE_SELECT)) {
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT s.subId, s.subjectName, AVG(score) AS avg FROM grade g INNER JOIN subject s ON g.subId = s.subId GROUP BY subId");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String result1 = rs.getString("subId");
					String result2 = rs.getString("subjectName");
					String result3 = rs.getString("avg");
					String result = "subId:" + result1 + " subject:" + result2 + " avg:" + result3;
					resultList.add(result);
				}

			} else if (selectOption.equals(Type.ALL_STUDENT_AVERAGE_SELECT)) {
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT s.stuId, s.studentName, AVG(score) AS avg FROM grade g INNER JOIN student s ON g.stuId = s.stuId GROUP BY stuId");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String result1 = rs.getString("stuId");
					String result2 = rs.getString("studentName");
					String result3 = rs.getString("avg");
					String result = "stuId:" + result1 + " student:" + result2 + " avg:" + result3;
					resultList.add(result);
				}
			} else {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT stuId, subId, score FROM grade");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Grade grade = Grade.of(rs.getString("stuId"), rs.getString("subId"), rs.getString("score"));
					resultList.add(grade.toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}

	@Override
	public Grade getById(int stuId, int subId) {
		Grade grade = new Grade();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT stuId, subId, score FROM grade WHERE stuId=? AND subId=?");
			preparedStatement.setInt(1, stuId);
			preparedStatement.setInt(2, subId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				grade = Grade.of(rs.getString("stuId"), rs.getString("subId"), rs.getString("score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grade;
	}

	@Override
	public boolean add(Grade grade) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO grade(stuId, subId, grade) VALUES (?, ?, ?)");
			// Parameters start with 1
			preparedStatement.setInt(1, grade.getStuId());
			preparedStatement.setInt(2, grade.getSubId());
			preparedStatement.setInt(3, grade.getScore());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int stuId, int subId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM grade WHERE stuId=? AND subId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, stuId);
			preparedStatement.setInt(2, subId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Grade grade) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE grade SET grade=?" + "WHERE stuId=? AND subId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, grade.getScore());
			preparedStatement.setInt(2, grade.getStuId());
			preparedStatement.setInt(3, grade.getSubId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
