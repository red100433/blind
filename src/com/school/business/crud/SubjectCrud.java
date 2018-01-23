package com.school.business.crud;

import java.util.List;

import com.school.models.Subject;
import com.school.models.Type;

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

	public <T> List<T> insert(List<? super T> list) {
		if (list.size() != Type.LIMIT_SUBJECT & list.contains(temp) == false) {
			list.add((T)temp);
		}
		return (List<T>)list;
	}

	public <T> List<T> update(List<? super T> list, String changeName) {

		if (list.contains(temp) & (list.contains(new Subject(changeName)) == false)) {
			list.remove(temp);
			list.add((T)new Subject(changeName));
			return (List<T>)list;
		}

		return (List<T>)list;
	}

	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}

}
