package com.school.service;

import java.util.List;

import com.school.business.EmployeeCrudImp;
import com.school.custom.EmployeeCrud;
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

	public void writeFileSystem(List<Employee> empList) {
		new EmployeeDao().writeDataList(empList);
	}

	public void insert(EmployeeRequest empRequest) {
		List<Employee> empList = select();
		empList = init().insert(empList, empRequest);
		writeFileSystem(empList);
	}

	public void update(EmployeeRequest empRequest) {
		List<Employee> empList = select();
		empList = init().update(empList, empRequest);
		writeFileSystem(empList);
	}

	public void delete(EmployeeRequest empRequest) {
		List<Employee> empList = select();
		empList = init().delete(empList, empRequest);
		writeFileSystem(empList);
	}

	public List<Employee> select() {
		return new EmployeeDao().readDataList();
	}

	private EmployeeCrud init() {
		return new EmployeeCrudImp();
	}
}
