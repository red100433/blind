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

	public void addEmployee(Employee employee) {
		dao.add(employee);
	}

	public void updateEmployee(Employee employee) {
		dao.update(employee);
	}

	public void deleteEmployee(Employee employee) {
		dao.delete(employee.getEmpId());
	}

	public List<Employee> getAllEmployees() {
		return dao.getAllList();
	}

}
