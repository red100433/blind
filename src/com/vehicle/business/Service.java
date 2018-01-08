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
	Scanner scanner = new Scanner(System.in);

	List<Subject> subList;
	List<Employee> empList;
	List<Teacher> teacherList;
	List<Student> stuList;
	List<Grade> gradeList;

	public void start() {
		init();

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
					performanceManagement(s2, gradeList, stuList);
					break;
			}
		}
	}

	private void performanceManagement(String s2, List<Grade> gradeList, List<Student> stuList) {
		if (s2.equals("1")) {
			System.out.println("학생번호 >>");
			String stu_id = scanner.nextLine();
			System.out.println("과목번호 >>");
			String sub_id = scanner.nextLine();
			System.out.println("점수>>");
			String grade = scanner.nextLine();

			//TODO: 예외 처리 후
			gradeList.add(new Grade(Integer.parseInt(stu_id), Integer.parseInt(sub_id), Integer.parseInt(grade)));

			fs.writeListObject(gradeList, gradePath);

		} else if (s2.equals("2")) {
			System.out.println("수정할 학생>>");
			String fix_name = scanner.nextLine();
			System.out.println("과목 이름>>");
			String fix_sub = scanner.nextLine();

			for (Student stu : stuList) {
				//TODO: 만약 학생 목록에 없으면 그냥 나감.
				if (stu.getName().equals(fix_name)) {

				}
			}
			fs.writeListObject(gradeList, gradePath);

		} else if (s2.equals("3")) {
			System.out.println("입력된 성적중 삭제할 과목과 학생의 이름을 적어주세요");
			System.out.println("이름>>");
			String delete_name = scanner.nextLine();
			System.out.println("과목>>");
			String delete_sub = scanner.nextLine();

			//TODO: 삭제할 성적과 이름 대조

			fs.writeListObject(gradeList, gradePath);

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

	private void personnelManagement(String s2, List<Student> stuList2, List<Employee> empList2,
		List<Teacher> teacherList2) {
		//TODO: 학생, 교직원, 선생별 별도 관리
	}

	private void init() {
		subList = (List<Subject>)fs.readListObject(subPath);
		empList = (List<Employee>)fs.readListObject(empPath);
		teacherList = (List<Teacher>)fs.readListObject(teacherPath);
		stuList = (List<Student>)fs.readListObject(stuPath);
		gradeList = (List<Grade>)fs.readListObject(gradePath);
	}
}