package com.school.inter;

import java.util.List;

/**
 *
 * @author daeyun-jang
 *
 */

//fileSystem access interface
public interface DaoInterface {
	public List<?> readDataList();

	public void writeDataList(List<?> List);
}
