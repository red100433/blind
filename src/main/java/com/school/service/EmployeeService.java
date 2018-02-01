package com.school.service;

import java.util.List;

import com.school.dao.EmployeeDao;
import com.school.models.vo.Employee;

public class EmployeeService {
	private static EmployeeService t;

	public static EmployeeService getInstance() {
		synchronized (EmployeeService.class) {
			if (t == null) {
				t = new EmployeeService();
			}
		}
		return t;
	}

	private EmployeeService() {}

	public void addEmployee(Employee employee) {
		init().addEmployee(employee);
	}

	public void updateEmployee(Employee employee) {
		// id 값 없음
		init().updateEmployee(employee);
	}

	public void deleteEmployee(Employee employee) {
		// id 값 없음
		init().deleteEmployee(employee.getEmp_Id());
	}

	public List<Employee> getAllEmployees() {
		return init().getAllEmployees();
	}

	private EmployeeDao init() {
		return new EmployeeDao();
	}
}
