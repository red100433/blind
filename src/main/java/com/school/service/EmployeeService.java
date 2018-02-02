package com.school.service;

import java.util.List;

import com.school.dao.EmployeeDao;
import com.school.models.vo.Employee;

public class EmployeeService {
	private static EmployeeService t;
	private EmployeeDao dao = EmployeeDao.getInstance();

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
		dao.addEmployee(employee);
	}

	public void updateEmployee(Employee employee) {
		dao.updateEmployee(employee);
	}

	public void deleteEmployee(Employee employee) {
		dao.deleteEmployee(employee.getEmpId());
	}

	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}

}
