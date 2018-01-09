package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Teacher;

public class TeacherDao implements DaoInterface {
	static final String teacherPath = "teacherObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Teacher> readDataList() {
		return (List<Teacher>)fs.readListObject(teacherPath);
	}

	@Override
	public void writeDataList(List<?> teacherList) {
		fs.writeListObject(teacherList, teacherPath);
	}
}
