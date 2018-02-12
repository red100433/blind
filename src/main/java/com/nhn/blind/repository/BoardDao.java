package com.nhn.blind.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.blind.model.Board;

import reactor.core.publisher.Flux;

@Mapper
public interface BoardDao {
	List<Board> getList();
	boolean add(Board board);
	boolean update(Board board);
	boolean delete(Long id);
	Board getById(Long id);
	List<Board> getTitleList();
}
