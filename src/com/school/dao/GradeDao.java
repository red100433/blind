package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Type;
import com.school.models.vo.Grade;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeDao implements DaoInterface {
	static final String GRADE_PATH = Type.BASIC_PATH + "gradeObject.txt";
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
