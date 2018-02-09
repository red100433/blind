package com.nhn.school.service;

import java.util.List;

import com.nhn.school.dao.EmployeeDao;
import com.nhn.school.models.vo.Employee;

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
