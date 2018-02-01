package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.models.vo.Student;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentDao {
	private Connection connection;

	public StudentDao() {
		connection = DbConnection.getConnection();
	}

	public void addStudent(Student student) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("insert into student(subjectName, birth) values (?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.setString(2, student.getBirth());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int stu_Id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("delete from student where stu_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, stu_Id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("update student set studentName=?, birth=?" +
					"where stu_Id=?");
			// Parameters start with 1
			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.setString(2, student.getBirth());
			preparedStatement.setInt(3, student.getStu_Id());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getAllSubjects() {
		List<Student> stuList = new ArrayList<Student>();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("select * from student");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student stu = new Student();
				stu.setStu_Id(rs.getInt("stu_Id"));
				stu.setStudentName(rs.getString("studentName"));
				stu.setBirth(rs.getString("birth"));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stuList;
	}

	public Student getStudentById(int stu_Id) {
		Student stu = new Student();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from student where stu_Id=?");
			preparedStatement.setInt(1, stu_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				stu.setStu_Id(rs.getInt("stu_Id"));
				stu.setStudentName(rs.getString("studentName"));
				stu.setBirth(rs.getString("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stu;
	}
}
