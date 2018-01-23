package com.school.service;

import java.util.List;

import com.school.business.crud.EmployeeCrud;
import com.school.dao.EmployeeDao;
import com.school.models.Employee;
import com.school.models.request.EmployeeRequest;

public class EmployeeService {
	private static EmployeeService t;
	private List<Employee> empList;

	public static EmployeeService getInstance() {
		synchronized (EmployeeService.class) {
			if (t == null) {
				t = new EmployeeService();
			}
		}
		return t;
	}

	private EmployeeService() {
		this.empList = new EmployeeDao().readDataList();
	}

	public void writeFileSystem() {
		new EmployeeDao().writeDataList(empList);
	}

	public void insert(EmployeeRequest empRequest) {
		this.empList = new EmployeeCrud(empRequest.getName(), empRequest.getBirth()).insert(empList);
		writeFileSystem();
	}

	public void update(EmployeeRequest empRequest) {
		this.empList = new EmployeeCrud(empRequest.getName(), empRequest.getBirth())
			.update(empList, empRequest.getChangeName(), empRequest.getChangeBirth());
		writeFileSystem();
	}

	public void delete(EmployeeRequest empRequest) {
		this.empList = new EmployeeCrud(empRequest.getName(), empRequest.getBirth()).delete(empList);
		writeFileSystem();
	}

	public List<Employee> select() {
		return this.empList;
	}
}
