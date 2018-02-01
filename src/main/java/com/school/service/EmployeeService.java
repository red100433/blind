package com.school.service;

import java.util.List;

import com.school.dao.EmployeeDao;
import com.school.models.request.EmployeeRequest;
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

	public void addEmployee(EmployeeRequest empRequest) {
		init().addEmployee(new Employee(empRequest.getName(), empRequest.getBirth()));
	}

	public void updateEmployee(EmployeeRequest empRequest) {
		// id 값 없음
		init().updateEmployee(new Employee(empRequest.getName(), empRequest.getBirth()));
	}

	public void deleteEmployee(EmployeeRequest empRequest) {
		// id 값 없음
		init().deleteEmployee(1);
	}

	public List<Employee> getAllEmployees() {
		return init().getAllEmployees();
	}

	private EmployeeDao init() {
		return new EmployeeDao();
	}
}
