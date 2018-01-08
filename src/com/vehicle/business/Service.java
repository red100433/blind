package com.vehicle.business;

import java.util.List;
import java.util.Scanner;

import com.vehicle.models.Employee;
import com.vehicle.models.Grade;
import com.vehicle.models.Student;
import com.vehicle.models.Subject;
import com.vehicle.models.Teacher;

public class Service {
	static final String subPath = "subObject.txt";
	static final String empPath = "empObject.txt";
	static final String teacherPath = "teacherObject.txt";
	static final String stuPath = "stuObject.txt";
	static final String gradePath = "gradeObject.txt";

	FileSystem fs = FileSystem.getInstance();

	List<Subject> subList;
	List<Employee> empList;
	List<Teacher> teacherList;
	List<Student> stuList;
	List<Grade> gradeList;

	public void start() {
		//		init();

		//		List<Subject> sub = new ArrayList<>();
		//		sub.add(new Subject(123, "a"));
		//		sub.add(new Subject(12, "b"));
		//		sub.add(new Subject(124, "c"));
		//		fs.writeListObject(sub, "asdf");
		//		List<Subject> rev = (List<Subject>)fs.readListObject("askdf");

		//		rev.stream().forEach(s -> {
		//			System.out.println(s.getId());
		//			System.out.println(s.getName());
		//		});
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1.인원관리  2.과목관리  3.성적관리  99.종료>>");
			String s = scanner.nextLine();

			switch (s) {
				case "1":
					personnelManagement(stuList, empList, teacherList);
					break;
				case "2":
					subjectManagement(subList);
					break;
				case "3":
					performanceManagement(gradeList);
					break;
				case "99":

					return;
				default:

					break;
			}
		}
	}

	private void performanceManagement(List<Grade> gradeList2) {
		// TODO Auto-generated method stub

	}

	private void subjectManagement(List<Subject> subList2) {
		// TODO Auto-generated method stub

	}

	private void personnelManagement(List<Student> stuList2, List<Employee> empList2, List<Teacher> teacherList2) {
		// TODO Auto-generated method stub

	}

	private void init() {
		subList = (List<Subject>)fs.readListObject(subPath);
		empList = (List<Employee>)fs.readListObject(empPath);
		teacherList = (List<Teacher>)fs.readListObject(teacherPath);
		stuList = (List<Student>)fs.readListObject(stuPath);
		gradeList = (List<Grade>)fs.readListObject(gradePath);
	}
}