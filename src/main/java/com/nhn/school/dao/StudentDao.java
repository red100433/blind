package com.nhn.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.dao.custom.Dao;
import com.nhn.school.db.DBConnection;
import com.nhn.school.models.vo.Student;

/**
 *
 * @author daeyun-jang
 *
 */
@Mapper
public interface StudentDao {

	 List<Student> getAllList();

	 Student getById(int id);

	 boolean add(Student student);

	 boolean delete(int id);

	 boolean update(Student student);
}
