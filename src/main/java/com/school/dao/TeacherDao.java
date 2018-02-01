package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.models.vo.Teacher;

public class TeacherDao {
	private Connection connection;

	private static TeacherDao t;

	public static TeacherDao getInstance() {
		synchronized (TeacherDao.class) {
			if (t == null) {
				t = new TeacherDao();
			}
		}
		return t;
	}

	public TeacherDao() {
		connection = DbConnection.getConnection();
	}

	public void addTeacher(Teacher teacher) {
		try {

			if (teacher.getSub_Id() == 0) {
				PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO teacher(teacherName, birth) VALUES (?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO teacher(teacherName, birth, sub_Id) VALUES (?, ?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getSub_Id());
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeacher(int teacher_Id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM teacher WHERE teacher_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, teacher_Id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTeacher(Teacher teacher) {
		try {
			if (teacher.getSub_Id() == 0) {
				PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE teacher SET teacherName=?, birth=?" +
						"where teacher_Id=?");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getTeacher_Id());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE teacher SET teacherName=?, birth=? , sub_Id=?" +
						"WHERE teacher_Id=?");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getSub_Id());
				preparedStatement.setInt(4, teacher.getTeacher_Id());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Teacher> getAllTeachers() {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teacher");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Teacher teacher = Teacher.of(rs.getString("teacher_Id"), rs.getString("teacherName"),
					rs.getString("birth"), rs.getString("sub_Id"));
				teacherList.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherList;
	}

	public Teacher getTeacherById(int teacher_Id) {
		Teacher teacher = new Teacher();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM teacher WHERE teacher_Id=?");
			preparedStatement.setInt(1, teacher_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				teacher = Teacher.of(rs.getString("teacher_Id"), rs.getString("teacherName"),
					rs.getString("birth"), rs.getString("sub_Id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacher;
	}
}
