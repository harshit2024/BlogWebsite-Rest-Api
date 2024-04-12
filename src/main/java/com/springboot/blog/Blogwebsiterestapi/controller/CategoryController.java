package com.springboot.blog.Blogwebsiterestapi.controller;

import com.springboot.blog.Blogwebsiterestapi.payload.CategoryDto;
import com.springboot.blog.Blogwebsiterestapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto saveCategory=categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable ("id") Long categoryId){
        CategoryDto categoryDto=categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public  ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId,categoryDto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }



}
