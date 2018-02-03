package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DBConnection;
import com.school.models.vo.Teacher;

public class TeacherDao implements Dao<Teacher>{
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

	@Override
	public List<Teacher> getAllList() {
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

	@Override
	public Teacher getById(int id) {
		Teacher teacher = new Teacher();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT teacherId, teacherName, birth, subId FROM teacher WHERE teacherId=?");
			preparedStatement.setInt(1, id);
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

	@Override
	public boolean add(Teacher teacher) {
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
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM teacher WHERE teacherId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Teacher teacher) {
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
		return false;
	}
}
