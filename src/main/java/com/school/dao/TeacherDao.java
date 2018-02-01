package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.models.vo.Teacher;

public class TeacherDao {
	private Connection connection;

	public TeacherDao() {
		connection = DbConnection.getConnection();
	}

	public void addTeacher(Teacher teacher) {
		try {

			if (teacher.getSub_Id() == 0) {
				PreparedStatement preparedStatement = connection
					.prepareStatement("insert into teacher(teacherName, birth) values (?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("insert into teacher(teacherName, birth, sub_Id) values (?, ?, ?)");
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
				.prepareStatement("delete from teacher where teacher_Id=?");
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
					.prepareStatement("update teacher set teacherName=?, birth=?" +
						"where teacher_Id=?");
				// Parameters start with 1
				preparedStatement.setString(1, teacher.getTeacherName());
				preparedStatement.setString(2, teacher.getBirth());
				preparedStatement.setInt(3, teacher.getTeacher_Id());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection
					.prepareStatement("update teacher set teacherName=?, birth=? , sub_Id=?" +
						"where teacher_Id=?");
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
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from teacher");
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTeacher_Id(rs.getInt("teacher_Id"));
				teacher.setTeacherName(rs.getString("teacherName"));
				teacher.setBirth(rs.getString("birth"));
				teacher.setSub_Id(rs.getInt("sub_Id"));
				teacherList.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(teacherList);
		return teacherList;
	}

	public Teacher getTeacherById(int teacher_Id) {
		Teacher teacher = new Teacher();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("select * from teacher where teacher_Id=?");
			preparedStatement.setInt(1, teacher_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				teacher.setTeacher_Id(rs.getInt("teacher_Id"));
				teacher.setTeacherName(rs.getString("teacherName"));
				teacher.setBirth(rs.getString("birth"));
				teacher.setSub_Id(rs.getInt("sub_Id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacher;
	}
}
