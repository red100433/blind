package com.school.business.crud;

import java.util.List;

import com.school.models.Student;
import com.school.models.Type;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentCrud {
	private String tempName;
	private String tempBirth;
	private Student temp;

	public StudentCrud(String studentName, String birth) {
		this.tempName = studentName;
		this.tempBirth = birth;
		this.temp = new Student(tempName, tempBirth);
	}

	public List<Student> insert(List<Student> list) {
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}
		return list;
	}

	public List<Student> update(List<Student> list, String changeName, String changeBirth) {
		Student change = new Student(changeName, changeBirth);

		if (list.contains(temp) & (list.contains(change) == false)) {
			list.remove(temp);
			list.add(change);
		}
		return list;
	}

	public List<Student> delete(List<Student> list) {
		list.remove(temp);
		return list;
	}

}
