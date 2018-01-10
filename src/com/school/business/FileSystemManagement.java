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

public class FileSystemManagement {

	public void excute(String management, int person, List<Student> stuList, List<Employee> empList,
		List<Teacher> teacherList, List<Subject> subList, List<Grade> gradeList) {
		switch (management) {
			case "1":
				if (person == 1) {
					new StudentDao().writeDataList(stuList);
				} else if (person == 2) {
					new EmployeeDao().writeDataList(empList);
				} else if (person == 3) {
					new TeacherDao().writeDataList(teacherList);
				}
				break;
			case "2":
				new SubjectDao().writeDataList(subList);
				break;
			case "3":
				new GradeDao().writeDataList(gradeList);
				break;
		}

	}
}
