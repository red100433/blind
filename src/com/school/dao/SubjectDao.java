package com.school.dao;

import java.util.List;

import com.school.business.FileSystem;
import com.school.models.Subject;

public class SubjectDao {
	static final String subPath = "subObject.txt";

	FileSystem fs = FileSystem.getInstance();

	public List<Subject> readDataList() {
		return (List<Subject>)fs.readListObject(subPath);
	}

	public void writeDataList(List<Subject> subList) {
		fs.writeListObject(subList, subPath);
	}
}
