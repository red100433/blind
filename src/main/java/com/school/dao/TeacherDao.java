package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DBConnection;
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
		connection = DBConnection.getConnection();
	}

	public void addTeacher(Teacher teacher) {
		try {

			if (teacher.getSubId() == 0) {
				PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO teacher(teacherName, birth) VALUES (?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO teacher(teacherName, birth, subId) VALUES (?, ?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getSubId());
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeacher(int teacherId) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM teacher WHERE teacherId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, teacherId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTeacher(Teacher teacher) {
		try {
			if (teacher.getSubId() == 0) {
				PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE teacher SET teacherName=?, birth=?" +
						"where teacherId=?");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getTeacherId());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE teacher SET teacherName=?, birth=? , subId=?" +
						"WHERE teacherId=?");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getSubId());
				preparedStatement.setInt(4, teacher.getTeacherId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Teacher> getAllTeachers() {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT teacherId, teacherName, birth, subId FROM teacher");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Teacher teacher = Teacher.of(rs.getString("teacherId"), rs.getString("teacherName"),
					rs.getString("birth"), rs.getString("subId"));
				teacherList.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherList;
	}

	public Teacher getTeacherById(int teacherId) {
		Teacher teacher = new Teacher();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT teacherId, teacherName, birth, subId FROM teacher WHERE teacherId=?");
			preparedStatement.setInt(1, teacherId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				teacher = Teacher.of(rs.getString("teacherId"), rs.getString("teacherName"),
					rs.getString("birth"), rs.getString("subId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacher;
	}
}
