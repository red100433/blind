package com.school.dao;

import java.util.List;

import com.school.custom.DaoInterface;
import com.school.filesystem.FileSystem;
import com.school.models.Type;
import com.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeDao implements DaoInterface {
	static final String EMP_PATH = Type.BASIC_PATH + "empObject.txt";
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
