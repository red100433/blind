package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
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
		connection = DbConnection.getConnection();
	}

	public void addSubject(Subject subject) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("insert into subject(subjectName) values (?)");
			// Parameters start with 1
			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSubject(int sub_Id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("delete from subject where sub_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, sub_Id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSubject(Subject subject) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("update subject set subjectName=?" +
					"where sub_Id=?");
			// Parameters start with 1
			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.setInt(2, subject.getSub_Id());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Subject> getAllSubjects() {
		List<Subject> subList = new ArrayList<Subject>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from subject");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setSub_Id(rs.getInt("sub_Id"));
				sub.setSubjectName(rs.getString("subjectName"));
				subList.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subList;
	}

	public Subject getSubjectById(int sub_Id) {
		Subject sub = new Subject();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from subject where sub_Id=?");
			preparedStatement.setInt(1, sub_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				sub.setSub_Id(rs.getInt("sub_Id"));
				sub.setSubjectName(rs.getString("subjectName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sub;
	}

}
