package com.springboot.blog.Blogwebsiterestapi.service;

import com.springboot.blog.Blogwebsiterestapi.payload.PostDto;
import com.springboot.blog.Blogwebsiterestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
     PostDto createPost(PostDto postDto);

     PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir);

     PostDto getPostById(long id);

     PostDto updatePost(PostDto postDto,long id);

     void deletePostBYId(long id);

     List<PostDto> getPostByCategoryId(Long categoryId);
}
