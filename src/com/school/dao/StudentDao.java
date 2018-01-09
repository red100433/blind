package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.models.Student;

public class StudentDao {
	static final String stuPath = "stuObject.txt";
	FileSystem fs = FileSystem.getInstance();

	public List<Student> readDataList() {
		return (List<Student>)fs.readListObject(stuPath);
	}

	public void writeDataList(List<Student> stuList) {
		fs.writeListObject(stuList, stuPath);
	}
}
