package com.springboot.blog.Blogwebsiterestapi.service;

import com.springboot.blog.Blogwebsiterestapi.entity.Comment;
import com.springboot.blog.Blogwebsiterestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto>  getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId,long Id);

    CommentDto updateComment(long postId,long commentId,CommentDto requestedComment);

    void deleteComment(long postId,long commentId);
}
