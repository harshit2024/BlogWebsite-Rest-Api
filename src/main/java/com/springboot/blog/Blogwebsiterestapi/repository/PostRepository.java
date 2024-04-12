package com.springboot.blog.Blogwebsiterestapi.repository;

import com.springboot.blog.Blogwebsiterestapi.entity.Post;
import com.springboot.blog.Blogwebsiterestapi.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCategoryId(Long categoryId);
}
