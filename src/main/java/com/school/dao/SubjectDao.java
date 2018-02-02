package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DBConnection;
import com.school.models.vo.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectDao {
	private Connection connection;

	private static SubjectDao t;

	public static SubjectDao getInstance() {
		synchronized (SubjectDao.class) {
			if (t == null) {
				t = new SubjectDao();
			}
		}
		return t;
	}

	public SubjectDao() {
		connection = DBConnection.getConnection();
	}

	public void addSubject(Subject subject) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO subject(subjectName) VALUES (?)");
			// Parameters start with 1
			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSubject(int subId) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM subject WHERE subId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, subId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSubject(Subject subject) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE subject SET subjectName=?" +
					"WHERE subId=?");
			// Parameters start with 1
			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.setInt(2, subject.getSubId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Subject> getAllSubjects() {
		List<Subject> subList = new ArrayList<Subject>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT subId, subjectName FROM subject");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject sub = Subject.of(rs.getString("subId"), rs.getString("subjectName"));
				subList.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subList;
	}

	public Subject getSubjectById(int subId) {
		Subject sub = new Subject();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT subId, subjectName FROM subject WHERE subId=?");
			preparedStatement.setInt(1, subId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				sub = Subject.of(rs.getString("subId"), rs.getString("subjectName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sub;
	}

}
