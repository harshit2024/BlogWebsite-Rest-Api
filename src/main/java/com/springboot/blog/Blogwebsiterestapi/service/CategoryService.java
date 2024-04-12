package com.springboot.blog.Blogwebsiterestapi.service;

import com.springboot.blog.Blogwebsiterestapi.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto addCategory(CategoryDto categoryDto);
    public CategoryDto getCategory(Long categoryId);
    public List<CategoryDto> getAllCategory();

    public CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto);
    public String deleteCategory(Long categoryId);


}
