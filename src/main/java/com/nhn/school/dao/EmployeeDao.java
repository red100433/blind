package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
@Mapper
public interface EmployeeDao {

	List<Employee> getAllList();

	 Employee getById(int id);

	 boolean add(Employee employee);

	 boolean delete(int id);

	 boolean update(Employee employee);
}
