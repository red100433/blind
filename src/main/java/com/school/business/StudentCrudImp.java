package com.school.business;

import java.util.List;

import com.school.custom.StudentCrud;
import com.school.models.Type;
import com.school.models.request.StudentRequest;
import com.school.models.vo.Student;
import com.school.service.GradeService;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentCrudImp implements StudentCrud {

	public StudentCrudImp() {}

	/* (non-Javadoc)
	 * @see com.school.business.crud.StudentCrud#insert(java.util.List)
	 */
	@Override
	public List<Student> insert(List<Student> list, StudentRequest stuRequest) {
		Student temp = new Student(stuRequest.getName(), stuRequest.getBirth());
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.StudentCrud#update(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Student> update(List<Student> list, StudentRequest stuRequest) {
		Student temp = new Student(stuRequest.getName(), stuRequest.getBirth());
		Student change = new Student(stuRequest.getChangeName(), stuRequest.getChangeBirth());

		if (list.contains(temp) & (list.contains(change) == false)) {
			GradeService.getInstance().update(stuRequest.getName(), stuRequest.getChangeName());
			list.remove(temp);
			list.add(change);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.StudentCrud#delete(java.util.List)
	 */
	@Override
	public List<Student> delete(List<Student> list, StudentRequest stuRequest) {
		Student temp = new Student(stuRequest.getName(), stuRequest.getBirth());
		GradeService.getInstance().delete(stuRequest.getName());
		list.remove(temp);
		return list;
	}

}
