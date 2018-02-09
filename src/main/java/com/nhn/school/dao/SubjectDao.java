package com.nhn.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nhn.school.dao.custom.Dao;
import com.nhn.school.db.DBConnection;
import com.nhn.school.models.vo.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectDao implements Dao<Subject>{
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



	@Override
	public List<Subject> getAllList() {
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

	@Override
	public Subject getById(int id) {
		Subject sub = new Subject();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT subId, subjectName FROM subject WHERE subId=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				sub = Subject.of(rs.getString("subId"), rs.getString("subjectName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sub;
	}

	@Override
	public boolean add(Subject subject) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO subject(subjectName) VALUES (?)");
			// Parameters start with 1
			preparedStatement.setString(1, subject.getSubjectName());
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
				.prepareStatement("DELETE FROM subject WHERE subId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Subject subject) {
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
		return false;
	}

}
