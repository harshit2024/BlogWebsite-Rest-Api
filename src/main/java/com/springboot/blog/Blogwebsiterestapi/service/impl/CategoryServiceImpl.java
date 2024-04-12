package com.springboot.blog.Blogwebsiterestapi.service.impl;

import com.springboot.blog.Blogwebsiterestapi.entity.Category;
import com.springboot.blog.Blogwebsiterestapi.exception.ResourceNotFoundException;
import com.springboot.blog.Blogwebsiterestapi.payload.CategoryDto;
import com.springboot.blog.Blogwebsiterestapi.repository.CreateRepository;
import com.springboot.blog.Blogwebsiterestapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CreateRepository createRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CreateRepository createRepository, ModelMapper modelMapper) {
        this.createRepository = createRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto,Category.class);
        Category saveCategory=createRepository.save(category);
        return modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category =createRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }



    @Override

    public List<CategoryDto> getAllCategory() {

        List<Category> categoryList=createRepository.findAll();
        return  categoryList.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category=createRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categoty","id",categoryId));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(categoryDto.getId());

        Category saveCategory=createRepository.save(category);

        return modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category=createRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categoty","id",categoryId));
        createRepository.delete(category);
        return "Category Deleted Successfully";
    }


}
