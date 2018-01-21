package com.school.service;

import java.util.List;

import com.school.business.Service;
import com.school.business.crud.EmployeeCrud;
import com.school.business.crud.StudentCrud;
import com.school.dao.EmployeeDao;
import com.school.dao.StudentDao;
import com.school.dao.SubjectDao;
import com.school.models.Employee;
import com.school.models.Student;

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
	
	public void insert(String employeeName, String birth) {
		this.empList = new EmployeeCrud(employeeName, birth).insert(empList);
		writeFileSystem();
	}
	
	public void update(String employeeName, String birth, String changeName, String changeBirth) {
		this.empList = new EmployeeCrud(employeeName, birth)
							.update(empList, changeName, changeBirth);
		writeFileSystem();
	}
	
	public void delete(String employeeName, String birth) {
		this.empList = new EmployeeCrud(employeeName, birth).delete(empList);
		writeFileSystem();
	}
	
	public List<Employee> select() {
		return this.empList;
	}
}
