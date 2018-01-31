package com.school.business;

import java.util.List;

import com.school.custom.SubjectCrud;
import com.school.models.request.SubjectRequest;
import com.school.models.vo.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrudImp implements SubjectCrud {

	/* (non-Javadoc)
	 * @see com.school.business.crud.SubjectCrud#insert(java.util.List)
	 */
	@Override
	public List<Subject> insert(List<Subject> list, SubjectRequest subjectRequest) {
		//		Subject temp = new Subject(subjectRequest.getName());
		//		if (list.size() != Type.LIMIT_SUBJECT & list.contains(temp) == false) {
		//			list.add(temp);
		//		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.SubjectCrud#update(java.util.List, java.lang.String)
	 */
	@Override
	public List<Subject> update(List<Subject> list, SubjectRequest subjectRequest) {
		//		Subject temp = new Subject(subjectRequest.getName());
		//		Subject change = new Subject(subjectRequest.getChangeName());
		//		if (list.contains(temp) & (list.contains(change) == false)) {
		//			GradeService.getInstance().update(subjectRequest.getName(), subjectRequest.getChangeName());
		//			TeacherService.getInstance().update(subjectRequest.getName(), subjectRequest.getChangeName());
		//			list.remove(temp);
		//			list.add(change);
		//		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.SubjectCrud#delete(java.util.List)
	 */
	@Override
	public List<Subject> delete(List<Subject> list, SubjectRequest subjectRequest) {
		//		Subject temp = new Subject(subjectRequest.getName());
		//		GradeService.getInstance().delete(subjectRequest.getName());
		//		TeacherService.getInstance().delete(subjectRequest.getName());
		//		list.remove(temp);
		return list;
	}

}
