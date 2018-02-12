package com.nhn.blind.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.blind.model.Comment;

import reactor.core.publisher.Flux;

@Mapper
public interface CommentDao {
	List<Comment> getList();
	boolean add(Comment comment);
	boolean update(Comment comment);
	boolean delete(Long id);
	List<Comment> getBoardCommentById(Long boardId);
}
