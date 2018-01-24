package com.school.business.crud;

import java.util.List;

import com.school.custom.StudentCrud;
import com.school.models.Type;
import com.school.models.vo.Grade;
import com.school.models.vo.Student;
import com.school.service.GradeService;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentCrudImp implements StudentCrud {
	private String tempName;
	private String tempBirth;
	private Student temp;
	private List<Grade> gradeList;

	public StudentCrudImp(String studentName, String birth) {
		this.tempName = studentName;
		this.tempBirth = birth;
		this.temp = new Student(tempName, tempBirth);
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.StudentCrud#insert(java.util.List)
	 */
	@Override
	public List<Student> insert(List<Student> list) {
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.StudentCrud#update(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Student> update(List<Student> list, String changeName, String changeBirth) {
		Student change = new Student(changeName, changeBirth);

		if (list.contains(temp) & (list.contains(change) == false)) {
			GradeService.getInstance().update(tempName, changeName);
			list.remove(temp);
			list.add(change);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.StudentCrud#delete(java.util.List)
	 */
	@Override
	public List<Student> delete(List<Student> list) {
		GradeService.getInstance().delete(tempName);
		list.remove(temp);
		return list;
	}

}
