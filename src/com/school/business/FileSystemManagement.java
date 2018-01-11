package com.school.business;

import java.util.List;

import com.school.dao.EmployeeDao;
import com.school.dao.GradeDao;
import com.school.dao.StudentDao;
import com.school.dao.SubjectDao;
import com.school.dao.TeacherDao;
import com.school.models.Employee;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.models.Teacher;

/**
 *
 * @author daeyun-jang
 *
 */

public class FileSystemManagement {
	private static final String PERSON_MANAGE = "1";
	private static final String SUBJECT_MANAGE = "2";
	private static final String GRADE_MANAGE = "3";
	private static final int STUDENT = 1;
	private static final int EMPLOYEE = 2;
	private static final int TEACHER = 3;

	//Thread 중 일때, Write
	public void wirte(String management, int person, List<Student> stuList, List<Employee> empList,
		List<Teacher> teacherList, List<Subject> subList, List<Grade> gradeList) {
		switch (management) {
			case PERSON_MANAGE:
				if (person == STUDENT) {
					new StudentDao().writeDataList(stuList);
				} else if (person == EMPLOYEE) {
					new EmployeeDao().writeDataList(empList);
				} else if (person == TEACHER) {
					new TeacherDao().writeDataList(teacherList);
				}
				break;
			case SUBJECT_MANAGE:
				new SubjectDao().writeDataList(subList);
				break;
			case GRADE_MANAGE:
				new GradeDao().writeDataList(gradeList);
				break;
		}

	}

	//Thread 끝날때, write
	public void wirte(List<Student> stuList, List<Employee> empList, List<Teacher> teacherList, List<Subject> subList,
		List<Grade> gradeList) {
		new StudentDao().writeDataList(stuList);
		new EmployeeDao().writeDataList(empList);
		new TeacherDao().writeDataList(teacherList);
		new SubjectDao().writeDataList(subList);
		new GradeDao().writeDataList(gradeList);
	}
}
