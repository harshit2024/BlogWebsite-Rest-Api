package com.springboot.blog.Blogwebsiterestapi.service.impl;


import com.springboot.blog.Blogwebsiterestapi.entity.Comment;
import com.springboot.blog.Blogwebsiterestapi.entity.Post;
import com.springboot.blog.Blogwebsiterestapi.exception.BlogApiException;
import com.springboot.blog.Blogwebsiterestapi.exception.ResourceNotFoundException;
import com.springboot.blog.Blogwebsiterestapi.payload.CommentDto;
import com.springboot.blog.Blogwebsiterestapi.repository.CommentRepository;
import com.springboot.blog.Blogwebsiterestapi.repository.PostRepository;
import com.springboot.blog.Blogwebsiterestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository ,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;
    }



    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto= mapper.map(comment,CommentDto.class);
//        CommentDto commentDto= new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto){
          Comment comment=mapper.map(commentDto,Comment.class);
//        Comment comment=new Comment();
//        comment.setId(commentDto.getId());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
        return  comment;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        //get the comment as entity
        Comment comment =mapToEntity(commentDto);


        //get the post using post id
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        //set the post to comment entity
        comment.setPost(post);


        //save this comment to the db using postId
        Comment newComment=commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments=commentRepository.findByPostId(postId);
        return comments.stream().map((comment)->mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long Id) {

        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        Comment comment =commentRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("Comment","id",Id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong the post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto requestedComment) {

        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        Comment comment =commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong the post");
        }
        comment.setName(requestedComment.getName());
        comment.setEmail(requestedComment.getEmail());
        comment.setBody(requestedComment.getBody());

        Comment updatedComment=commentRepository.save(comment);

        return mapToDto(updatedComment);
    }
    public void deleteComment(long postId,long commentId){
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        Comment comment =commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong the post");
        }

        commentRepository.delete(comment);
    }
}
