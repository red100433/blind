package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.models.Type;
import com.school.models.vo.Grade;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeDao {
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
		connection = DbConnection.getConnection();
	}

	public void addGrade(Grade grade) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO grade(stu_Id, sub_Id, grade) VALUES (?, ?, ?)");
			// Parameters start with 1
			preparedStatement.setInt(1, grade.getStu_Id());
			preparedStatement.setInt(2, grade.getSub_Id());
			preparedStatement.setInt(3, grade.getGrade());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGrade(int stu_Id, int sub_Id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM grade WHERE stu_Id=? AND sub_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, stu_Id);
			preparedStatement.setInt(2, sub_Id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateGrade(Grade grade) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE grade SET grade=?" +
					"WHERE stu_Id=? AND sub_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, grade.getGrade());
			preparedStatement.setInt(2, grade.getStu_Id());
			preparedStatement.setInt(3, grade.getSub_Id());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Grade> getAllGrades() {
		List<Grade> gradeList = new ArrayList<Grade>();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM grade");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Grade grade = Grade.of(rs.getString("stu_Id"), rs.getString("sub_Id"), rs.getString("grade"));
				gradeList.add(grade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeList;
	}

	public Grade getGradeById(int stu_Id, int sub_Id) {
		Grade grade = new Grade();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM grade WHERE stu_Id=? AND sub_Id=?");
			preparedStatement.setInt(1, stu_Id);
			preparedStatement.setInt(2, sub_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				grade = Grade.of(rs.getString("stu_Id"), rs.getString("sub_Id"), rs.getString("grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grade;
	}

	public List<String> selectOption(String selectOption) {
		List<String> resultList = new ArrayList<>();
		try {
			if (selectOption.equals(Type.ALL_SUBJECT_AVERAGE_SELECT)) {
				PreparedStatement preparedStatement = connection
					.prepareStatement(
						"SELECT s.sub_Id, s.subjectName, avg(grade) AS avg FROM grade g INNER JOIN subject s ON g.sub_Id = s.sub_Id GROUP BY sub_Id");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String result1 = rs.getString("sub_Id");
					String result2 = rs.getString("subjectName");
					String result3 = rs.getString("avg");
					String result = "sub_Id:" + result1 + " subject:" + result2 + " avg:" + result3;
					resultList.add(result);
				}

			} else if (selectOption.equals(Type.ALL_STUDENT_AVERAGE_SELECT)) {
				PreparedStatement preparedStatement = connection
					.prepareStatement(
						"SELECT s.stu_Id, s.studentName, avg(grade) AS avg FROM grade g INNER JOIN student s ON g.stu_Id = s.stu_Id GROUP BY stu_Id");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String result1 = rs.getString("stu_Id");
					String result2 = rs.getString("studentName");
					String result3 = rs.getString("avg");
					String result = "stu_Id:" + result1 + " student:" + result2 + " avg:" + result3;
					resultList.add(result);
				}
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM grade");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Grade grade = Grade.of(rs.getString("stu_Id"), rs.getString("sub_Id"), rs.getString("grade"));
					resultList.add(grade.toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}
}
