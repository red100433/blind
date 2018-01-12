package com.school.business.crud;

import java.util.List;
import java.util.stream.Collectors;

import com.school.handler.ScannerHandler;
import com.school.inter.CrudInterface;
import com.school.models.Subject;
import com.school.view.SubjectUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrud implements CrudInterface {
	private static final int LIMIT_SUBJECT = 100;
	private final ScannerHandler scanner = ScannerHandler.getInstance();
	private String tempName;
	private Subject temp;
	private SubjectUI subjectUi;

	public SubjectCrud() {
		this.subjectUi = new SubjectUI(scanner.getScanner());
		this.tempName = subjectUi.inputSubjectName();
		this.temp = new Subject(tempName);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		if (list.size() != LIMIT_SUBJECT) {
			list.add((T)temp);
		} else {
			this.subjectUi.limitSubject();
		}

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			Subject s = (Subject)e;
			if (s.equals(temp)) {
				String changeName = subjectUi.changeSubjectName();
				list.remove(new Subject(changeName));
				((Subject)e).setSubjectName(changeName);
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
