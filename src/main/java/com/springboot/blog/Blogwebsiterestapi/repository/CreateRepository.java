package com.springboot.blog.Blogwebsiterestapi.repository;

import com.springboot.blog.Blogwebsiterestapi.entity.Category;
import com.springboot.blog.Blogwebsiterestapi.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreateRepository extends JpaRepository<Category,Long> {


}
