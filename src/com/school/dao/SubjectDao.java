package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectDao implements DaoInterface {
	static final String SUB_PATH = "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\subObject.txt";
	//	static final String SUB_PATH = "subObject.txt";
	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Subject> readDataList() {
		return (List<Subject>)fs.readListObject(SUB_PATH);
	}

	@Override
	public void writeDataList(List<?> subList) {
		fs.writeListObject(subList, SUB_PATH);
	}
}
