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
import com.nhn.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
@Mapper
public interface EmployeeDao {

	List<Employee> findAll();

	 Employee getById(int id);

	 boolean add(Employee employee);

	 boolean delete(int id);

	 boolean update(Employee employee);
}
