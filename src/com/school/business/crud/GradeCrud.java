package com.school.business.crud;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.school.inter.CrudInterface;
import com.school.models.Grade;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempStudentName;
	private String tempSubjectName;
	private int tempGrade;
	private Grade temp;

	public GradeCrud() {
		System.out.println("학생 이름>>");
		this.tempStudentName = scanner.nextLine();
		System.out.println("과목 이름>>");
		this.tempSubjectName = scanner.nextLine();
		this.temp = new Grade(tempStudentName, tempSubjectName);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		System.out.println("성적>>");
		this.tempGrade = Integer.parseInt(scanner.nextLine());
		this.temp = new Grade(tempStudentName, tempSubjectName, tempGrade);
		list.add((T)temp);

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			if (e.equals(temp)) {
				System.out.println("변경될 학생이름>>");
				String setStudentName = scanner.nextLine();
				System.out.println("변경될 과목이름>>");
				String setSubjectName = scanner.nextLine();
				System.out.println("변경될 점수>>");
				String setGrade = scanner.nextLine();
				list.remove(new Grade(setStudentName, setSubjectName));
				((Grade)e).setStudentName(setStudentName);
				((Grade)e).setSubjectName((setSubjectName));
				((Grade)e).setGrade(Integer.parseInt(setGrade));
				return (List<T>)list;
			}
		}
		return (List<T>)list;
	}

	@Override
	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}

}
