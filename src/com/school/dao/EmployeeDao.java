package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeDao implements DaoInterface {
	static final String EMP_PATH = "C:\\Users\\USER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\empObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Employee> readDataList() {
		return (List<Employee>)fs.readListObject(EMP_PATH);
	}

	@Override
	public void writeDataList(List<?> empList) {
		fs.writeListObject(empList, EMP_PATH);
	}
}
