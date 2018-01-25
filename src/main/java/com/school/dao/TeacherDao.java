package com.school.dao;

import java.util.List;

import com.school.custom.DaoInterface;
import com.school.filesystem.FileSystem;
import com.school.models.Type;
import com.school.models.vo.Teacher;

public class TeacherDao implements DaoInterface {
	static final String TEACHER_PATH = Type.BASIC_PATH + "teacherObject.txt";
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
