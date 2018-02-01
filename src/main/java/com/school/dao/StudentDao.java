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

	private static StudentDao t;

	public static StudentDao getInstance() {
		synchronized (StudentDao.class) {
			if (t == null) {
				t = new StudentDao();
			}
		}
		return t;
	}

	public StudentDao() {
		connection = DbConnection.getConnection();
	}

	public void addStudent(Student student) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO student(subjectName, birth) VALUES (?, ?)");
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
				.prepareStatement("DELETE FROM student WHERE stu_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, stu_Id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//TODO query 대문자 template method
	public void updateStudent(Student student) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE student SET studentName=?, birth=?" +
					"WHERE stu_Id=?");
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
				.prepareStatement("SELECT * FROM student");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student stu = Student.of(rs.getString("stu_Id"), rs.getString("studentName"), rs.getString("birth"));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stuList;
	}

	//TODO _ 제거
	public Student getStudentById(int stu_Id) {
		Student stu = new Student();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE stu_Id=?");
			preparedStatement.setInt(1, stu_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				stu = Student.of(rs.getString("stu_Id"), rs.getString("studentName"), rs.getString("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stu;
	}
}
