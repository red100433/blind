package com.school.business.crud;

import java.util.List;

import com.school.models.Type;
import com.school.models.vo.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrud {
	private String tempName;
	private Subject temp;

	public SubjectCrud(String subName) {
		this.tempName = subName;
		this.temp = new Subject(tempName);
	}

	public List<Subject> insert(List<Subject> list) {
		if (list.size() != Type.LIMIT_SUBJECT & list.contains(temp) == false) {
			list.add(temp);
		}
		return list;
	}

	public List<Subject> update(List<Subject> list, String changeName) {
		Subject change = new Subject(changeName);
		if (list.contains(temp) & (list.contains(change) == false)) {
			list.remove(temp);
			list.add(change);
		}

		return list;
	}

	public List<Subject> delete(List<Subject> list) {
		list.remove(temp);
		return list;
	}

}
