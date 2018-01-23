package com.school.business.crud;

import java.util.List;

import com.school.inter.custom.SubjectCrud;
import com.school.models.Type;
import com.school.models.vo.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrudImp implements SubjectCrud {
	private String tempName;
	private Subject temp;

	public SubjectCrudImp(String subName) {
		this.tempName = subName;
		this.temp = new Subject(tempName);
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.SubjectCrud#insert(java.util.List)
	 */
	@Override
	public List<Subject> insert(List<Subject> list) {
		if (list.size() != Type.LIMIT_SUBJECT & list.contains(temp) == false) {
			list.add(temp);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.SubjectCrud#update(java.util.List, java.lang.String)
	 */
	@Override
	public List<Subject> update(List<Subject> list, String changeName) {
		Subject change = new Subject(changeName);
		if (list.contains(temp) & (list.contains(change) == false)) {
			list.remove(temp);
			list.add(change);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.SubjectCrud#delete(java.util.List)
	 */
	@Override
	public List<Subject> delete(List<Subject> list) {
		list.remove(temp);
		return list;
	}

}
