package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Employee;

public class EmployeeDao implements DaoInterface {
	static final String empPath = "empObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Employee> readDataList() {
		return (List<Employee>)fs.readListObject(empPath);
	}

	@Override
	public void writeDataList(List<?> empList) {
		fs.writeListObject(empList, empPath);
	}
}
