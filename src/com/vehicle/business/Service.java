package com.vehicle.business;

import java.util.ArrayList;
import java.util.List;

import com.vehicle.personnel.Employee;
import com.vehicle.personnel.Student;
import com.vehicle.personnel.Subject;
import com.vehicle.personnel.Teacher;
import com.vehicle.superintend.Grade;

public class Service {
	static final String subPath = "subObject.txt";
	static final String empPath = "empObject.txt";
	static final String teacherPath = "teacherObject.txt";
	static final String stuPath = "stuObject.txt";
	static final String gradePath = "gradeObject.txt";

	List<Subject> subList;
	List<Employee> empList;
	List<Teacher> teacherList;
	List<Student> stuList;
	List<Grade> gradeList;

	public void start() {
		FileSystem fs = new FileSystem();
		List<Subject> sub = new ArrayList<>();
		sub.add(new Subject(123, "a"));
		sub.add(new Subject(12, "b"));
		sub.add(new Subject(124, "c"));
		fs.writeListObject(sub, "asdf");
		List<Subject> rev = (List<Subject>)fs.readListObject("askdf");

		rev.stream().forEach(s -> {
			System.out.println(s.getId());
			System.out.println(s.getName());
		});

	}
}