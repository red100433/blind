package com.school.business;

import java.util.List;
import java.util.Scanner;

import com.school.dao.EmployeeDao;
import com.school.dao.GradeDao;
import com.school.dao.StudentDao;
import com.school.dao.SubjectDao;
import com.school.dao.TeacherDao;
import com.school.inter.DaoInterface;
import com.school.models.Employee;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.models.Teacher;

public class Service {

	Scanner scanner = new Scanner(System.in);
	DaoInterface dao;
	List<Employee> empList;
	List<Grade> gradeList;
	List<Student> stuList;
	List<Subject> subList;
	List<Teacher> teacherList;

	public Service() {
		this.empList = new EmployeeDao().readDataList();
		this.gradeList = new GradeDao().readDataList();
		this.stuList = new StudentDao().readDataList();
		this.subList = new SubjectDao().readDataList();
		this.teacherList = new TeacherDao().readDataList();
	}

	public void programStart() {
		while (true) {
			System.out.println("1.인원관리  2.과목관리  3.성적관리  99.종료>>");
			String s = scanner.nextLine();
			if (s.equals("99")) {
				scanner.close();
				return;
			}

			System.out.println("1.입력   2.수정  3.삭제  4.조회>>");
			String s2 = scanner.nextLine();

			switch (s) {
				case "1":
					personnelManagement(s2, stuList, empList, teacherList);
					break;

				case "2":
					subjectManagement(s2, subList);
					break;

				case "3":
					performanceManagement(s2, gradeList, stuList, subList);
					break;
			}
		}
	}

	private void performanceManagement(String s2, List<Grade> gradeList, List<Student> stuList, List<Subject> subList) {

		if (s2.equals("1")) {
			System.out.println("학생이름 >>");
			String stu_name = scanner.nextLine();
			System.out.println("과목이름 >>");
			String sub_name = scanner.nextLine();
			System.out.println("점수>>");
			String grade = scanner.nextLine();

			int sub_id = -1;
			int stu_id = -1;

			//TODO: 예외 처리 후

			for (Student stu : stuList) {
				if (stu.getStudentName().equals(stu_name)) {
					stu_id = stu.getStudentId();
				}
			}
			for (Subject sub : subList) {
				if (sub.getSubjectName().equals(sub_name)) {
					sub_id = sub.getSubjectId();
				}
			}

			for (Grade g_ob : gradeList) {
				if (grade.equals(new Grade(stu_name, sub_name))) {
					break;
				}
				if (g_ob == gradeList.get(gradeList.size() - 1)) {
					gradeList.add(new Grade(stu_name, sub_name, Integer.parseInt(grade)));

					dao.writeDataList(gradeList);
				}
			}

		} else if (s2.equals("2")) {
			System.out.println("수정할 학생>>");
			String fix_name = scanner.nextLine();
			System.out.println("과목 이름>>");
			String fix_sub = scanner.nextLine();
			System.out.println("점수 >>");
			String fix_grade = scanner.nextLine();

			//TODO: 예외처리 바꿔야됌
			for (Student stu : stuList) {

				if (stu.getStudentName().equals(fix_name)) {
					for (Grade grade : gradeList) {
						if (grade.getStudentName() == stu.getStudentName()) {

						}
					}
				}
			}
			dao.writeDataList(gradeList);

		} else if (s2.equals("3")) {
			System.out.println("삭제할 과목과 학생의 이름을 적어주세요");
			System.out.println("이름>>");
			String delete_name = scanner.nextLine();
			System.out.println("과목>>");
			String delete_sub = scanner.nextLine();

			//TODO: 삭제할 과목과 이름 대조
			for (Student stu : stuList) {
				if (stu.getStudentName().equals(delete_name)) {

				}
			}
			for (Subject sub : subList) {
				if (sub.getSubjectName().equals(delete_sub)) {

				}
			}

			for (Grade g_ob : gradeList) {
				if (g_ob.equals(new Grade(delete_name, delete_sub))) {
					gradeList.remove(g_ob);
				}
			}

			dao.writeDataList(gradeList);

		} else if (s2.equals("4")) {
			//전체 조회
			gradeList.stream().forEach(System.out::println);

			//TODO: 학생별 조회
			//TODO: 학생 평균
			//TODO: 전체 평균
		}
	}

	private void subjectManagement(String s2, List<Subject> subList) {
		//TODO: 입력 수정 삭제 조회
	}

	private void personnelManagement(String s2, List<Student> stuList, List<Employee> empList,
		List<Teacher> teacherList) {
		//TODO: 학생, 교직원, 선생별 별도 관리
		System.out.println("1.학생  2.교직원 3.선생님 >>");
		String group = scanner.nextLine();

		switch (s2) {
			case "1":

				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				if (group.equals("1")) {
					stuList.stream().forEach(System.out::println);
				} else if (group.equals("2")) {
					empList.stream().forEach(System.out::println);
				} else if (group.equals("3")) {
					teacherList.stream().forEach(System.out::println);
				}
				break;
		}
	}

}