package com.school.business.crud;

import java.util.List;
import java.util.stream.Collectors;

import com.school.handler.ScannerHandler;
import com.school.inter.CrudInterface;
import com.school.models.Student;
import com.school.view.StudentUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentCrud {
	private static final int LIMIT_STUDENT = 1000;
	private final ScannerHandler scanner = ScannerHandler.getInstance();
	private String tempName;
	private String tempBirth;
	private Student temp;
	private StudentUI studentUI;

	public StudentCrud() {
		this.studentUI = new StudentUI(scanner.getScanner());
		this.tempName = studentUI.inputStudentName();
		this.tempBirth = studentUI.inputStudentBirth();
		this.temp = new Student(tempName, tempBirth);
	}
	public StudentCrud(String studentName, String birth) {
		this.tempName = studentName;
		this.tempBirth = birth;
		this.temp = new Student(tempName, tempBirth);
	}

	public <T> List<T> insert(List<? super T> list) {
		if (list.size() != LIMIT_STUDENT) {
			list.add((T)temp);
		} else {
			this.studentUI.limitStudent();
		}

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			if (e.equals(temp)) {
				String changeName = studentUI.changeStudentName();
				String changeBirth = studentUI.changeStudentBirth();
				list.remove(new Student(changeName, changeBirth));
				((Student)e).setStudentName(changeName);
				((Student)e).setBirth((changeBirth));
				return (List<T>)list;
			}
		}
		return (List<T>)list;
	}
	
	public <T> List<T> update(List<? super T> list, String changeName, String changeBirth) {
		for (Object e : list) {
			if (e.equals(temp)) {
				list.remove(new Student(changeName, changeBirth));
				((Student)e).setStudentName(changeName);
				((Student)e).setBirth((changeBirth));
				return (List<T>)list;
			}
		}
		return (List<T>)list;
	}

	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}

}
