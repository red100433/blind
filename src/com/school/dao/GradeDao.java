package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.models.Grade;

public class GradeDao {
	static final String gradePath = "gradeObject.txt";
	FileSystem fs = FileSystem.getInstance();

	public List<Grade> readDataList() {
		return (List<Grade>)fs.readListObject(gradePath);
	}

	public void writeDataList(List<Grade> gradeList) {
		fs.writeListObject(gradeList, gradePath);
	}
}
