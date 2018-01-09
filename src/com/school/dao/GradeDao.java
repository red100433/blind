package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Grade;

public class GradeDao implements DaoInterface {
	static final String gradePath = "gradeObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Grade> readDataList() {
		return (List<Grade>)fs.readListObject(gradePath);
	}

	@Override
	public void writeDataList(List<?> gradeList) {
		fs.writeListObject(gradeList, gradePath);
	}
}
