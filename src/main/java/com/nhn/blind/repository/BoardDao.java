package com.nhn.blind.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nhn.blind.model.Board;

import reactor.core.publisher.Flux;

@Mapper
public interface BoardDao {
	List<Board> getList();
	boolean add(Board board);
	boolean update(Board board);
	boolean delete(Board board);
	Board getById(Board board);
	List<Board> getTitleList();
}
