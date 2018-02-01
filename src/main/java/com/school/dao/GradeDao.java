package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public GradeDao() {
		connection = DbConnection.getConnection();
	}

	public void addGrade(Grade grade) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("insert into grade(stu_Id, sub_Id, grade) values (?, ?, ?)");
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
				.prepareStatement("delete from grade where stu_Id=? and sub_Id=?");
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
				.prepareStatement("update grade set grade=?" +
					"where stu_Id=? and sub_Id=?");
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
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from grade");
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setStu_Id(rs.getInt("stu_Id"));
				grade.setSub_Id(rs.getInt("sub_Id"));
				grade.setGrade(rs.getInt("grade"));
				gradeList.add(grade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(gradeList);
		return gradeList;
	}

	public Grade getGradeById(int stu_Id, int sub_Id) {
		Grade grade = new Grade();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("select * from grade where stu_Id=? and sub_Id=?");
			preparedStatement.setInt(1, stu_Id);
			preparedStatement.setInt(2, sub_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				grade.setStu_Id(rs.getInt("stu_Id"));
				grade.setSub_Id(rs.getInt("sub_Id"));
				grade.setGrade(rs.getInt("grade"));
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
						"select s.sub_Id, s.subjectName, avg(grade) as avg from grade g inner join subject s on g.sub_Id = s.sub_Id group by sub_Id");
				ResultSet rs = preparedStatement.executeQuery();
				String result1 = rs.getString("sub_Id");
				String result2 = rs.getString("subjectName");
				String result3 = rs.getString("avg");
				String result = "sub_Id:" + result1 + " subject:" + result2 + " avg:" + result3;
				resultList.add(result);

			} else if (selectOption.equals(Type.ALL_STUDENT_AVERAGE_SELECT)) {
				PreparedStatement preparedStatement = connection
					.prepareStatement(
						"select s.stu_Id, s.studentName, avg(grade) as avg from grade g inner join student s on g.stu_Id = s.stu_Id group by stu_Id");
				ResultSet rs = preparedStatement.executeQuery();
				String result1 = rs.getString("stu_Id");
				String result2 = rs.getString("studentName");
				String result3 = rs.getString("avg");
				String result = "stu_Id:" + result1 + " student:" + result2 + " avg:" + result3;
				resultList.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}
}
