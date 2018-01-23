package com.school.business.crud;

import java.util.List;

import com.school.handler.ScannerHandler;
import com.school.models.Subject;
import com.school.view.SubjectUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrud {
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

	public SubjectCrud(String subName) {
		this.tempName = subName;
		this.temp = new Subject(tempName);
	}

	public <T> List<T> insert(List<? super T> list) {
		if (list.size() != LIMIT_SUBJECT & list.contains(temp) == false) {
			list.add((T)temp);
		}
		return (List<T>)list;
	}

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

	public <T> List<T> update(List<? super T> list, String changeName) {
		for (Object e : list) {
			if (e.equals(temp) & list.contains(new Subject(changeName)) == false) {
				list.remove(temp);
				list.add((T)new Subject(changeName));
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
