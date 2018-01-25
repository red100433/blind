package com.school.custom;

import java.util.List;

import com.school.models.request.SubjectRequest;
import com.school.models.vo.Subject;

public interface SubjectCrud {

	List<Subject> delete(List<Subject> list, SubjectRequest subjectRequest);

	List<Subject> update(List<Subject> list, SubjectRequest subjectRequest);

	List<Subject> insert(List<Subject> list, SubjectRequest subjectRequest);

}