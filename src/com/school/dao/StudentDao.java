package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Student;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentDao implements DaoInterface {
	static final String STU_PATH = "C:\\Users\\hey\\Desktop\\javawork\\basicJava\\stuObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Student> readDataList() {
		return (List<Student>)fs.readListObject(STU_PATH);
	}

	@Override
	public void writeDataList(List<?> stuList) {
		fs.writeListObject(stuList, STU_PATH);
	}
}
