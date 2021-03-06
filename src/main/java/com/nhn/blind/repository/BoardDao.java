package com.nhn.blind.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nhn.blind.model.Board;

@Mapper
public interface BoardDao {
	List<Board> getList(Long next);

	List<Board> getListAll();

	boolean add(Board board);

	boolean delete(Board board);

	Board getById(@Param("id") Long id, @Param("userId") int userId);
}
