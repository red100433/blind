package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.model.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
@Mapper
public interface EmployeeDao {

	List<Employee> findAll();

	Employee getById(int id);

	boolean save(Employee employee);

	boolean delete(int id);

	int count();
}
