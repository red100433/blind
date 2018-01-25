package com.school.service;

import java.util.List;

import com.school.business.crud.EmployeeCrudImp;
import com.school.custom.EmployeeCrud;
import com.school.dao.EmployeeDao;
import com.school.models.request.EmployeeRequest;
import com.school.models.vo.Employee;

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
		this.empList = init(empRequest).insert(empList);
		writeFileSystem();
	}

	public void update(EmployeeRequest empRequest) {
		this.empList = init(empRequest)
			.update(empList, empRequest.getChangeName(), empRequest.getChangeBirth());
		writeFileSystem();
	}

	public void delete(EmployeeRequest empRequest) {
		this.empList = init(empRequest).delete(empList);
		writeFileSystem();
	}

	public List<Employee> select() {
		return this.empList;
	}

	private EmployeeCrud init(EmployeeRequest empRequest) {
		return new EmployeeCrudImp(empRequest.getName(), empRequest.getBirth());
	}
}
