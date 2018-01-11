package com.school.business.crud;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.school.inter.CrudInterface;
import com.school.models.Subject;
import com.school.view.SubjectUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempName;
	private Subject temp;
	private SubjectUI subjectUi = new SubjectUI(scanner);

	public SubjectCrud() {
		this.tempName = subjectUi.inputSubjectName();
		this.temp = new Subject(tempName);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		list.add((T)temp);

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			Subject s = (Subject)e;
			if (s.equals(temp)) {
				String setName = subjectUi.changeSubjectName();
				list.remove(new Subject(setName));
				((Subject)e).setSubjectName(setName);
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
