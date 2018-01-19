package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Grade;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeDao implements DaoInterface {
	static final String GRADE_PATH = "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\gradeObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Grade> readDataList() {
		return (List<Grade>)fs.readListObject(GRADE_PATH);
	}

	@Override
	public void writeDataList(List<?> gradeList) {
		fs.writeListObject(gradeList, GRADE_PATH);
	}
}
