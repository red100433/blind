package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.models.Employee;

public class EmployeeDao {
	static final String empPath = "empObject.txt";
	FileSystem fs = FileSystem.getInstance();

	public List<Employee> readDataList() {
		return (List<Employee>)fs.readListObject(empPath);
	}
}
