package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Student;

public class StudentDao implements DaoInterface {
	static final String stuPath = "stuObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Student> readDataList() {
		return (List<Student>)fs.readListObject(stuPath);
	}

	@Override
	public void writeDataList(List<?> stuList) {
		fs.writeListObject(stuList, stuPath);
	}
}
