package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.EmployeeDao;
import com.nhn.school.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public void save(Employee employee) {
		dao.save(employee);
	}

	public void save(Employee employee, int id) {
		dao.save(employee, id);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Employee> findAll() {
		return dao.findAll();
	}

}
