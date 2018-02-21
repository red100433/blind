package com.nhn.blind.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.blind.model.Comment;

@Mapper
public interface CommentDao {
	boolean add(Comment comment);
	boolean delete(Comment comment);
	List<Comment> getBoardCommentById(Long boardId);
}
