package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.models.Teacher;

public class TeacherDao {
	static final String teacherPath = "teacherObject.txt";
	FileSystem fs = FileSystem.getInstance();

	public List<Teacher> readDataList() {
		return (List<Teacher>)fs.readListObject(teacherPath);
	}

	public void writeDataList(List<Teacher> teacherList) {
		fs.writeListObject(teacherList, teacherPath);
	}
}
