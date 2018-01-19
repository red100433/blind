package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Teacher;

public class TeacherDao implements DaoInterface {
	static final String TEACHER_PATH = "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\teacherObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Teacher> readDataList() {
		return (List<Teacher>)fs.readListObject(TEACHER_PATH);
	}

	@Override
	public void writeDataList(List<?> teacherList) {
		fs.writeListObject(teacherList, TEACHER_PATH);
	}
}
