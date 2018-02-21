package com.nhn.blind.cache;

import java.util.List;

public interface Cache<T> {
	public List<T> findGroup(Long commentGroupKey);
	public void init(long now);
}