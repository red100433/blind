package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.inter.DaoInterface;
import com.school.models.Subject;

public class SubjectDao implements DaoInterface {
	static final String subPath = "subObject.txt";

	FileSystem fs = FileSystem.getInstance();

	@Override
	public List<Subject> readDataList() {
		return (List<Subject>)fs.readListObject(subPath);
	}

	@Override
	public void writeDataList(List<?> subList) {
		fs.writeListObject(subList, subPath);
	}
}
