package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DBConnection;
import com.school.models.vo.Student;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentDao implements Dao<Student>{
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
		connection = DBConnection.getConnection();
	}


	@Override
	public List<Student> getAllList() {
		List<Student> stuList = new ArrayList<Student>();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT stuId, studentName, birth FROM student");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student stu = Student.of(rs.getString("stuId"), rs.getString("studentName"), rs.getString("birth"));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stuList;
	}

	@Override
	public Student getById(int id) {
		Student stu = new Student();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT stuId, studentName, birth FROM student WHERE stuId=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				stu = Student.of(rs.getString("stuId"), rs.getString("studentName"), rs.getString("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stu;
	}

	@Override
	public boolean add(Student student) {
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
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM student WHERE stuId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Student student) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE student SET studentName=?, birth=?" +
					"WHERE stuId=?");
			// Parameters start with 1
			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.setString(2, student.getBirth());
			preparedStatement.setInt(3, student.getStuId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
