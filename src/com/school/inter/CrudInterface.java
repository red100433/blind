package com.school.inter;

import java.util.List;

public interface CrudInterface {
	public <T> List<T> insert(List<? super T> list);

	public <T> List<T> update(List<? super T> list);

	public <T> List<T> delete(List<? super T> list);

}
