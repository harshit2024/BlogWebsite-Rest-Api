package com.springboot.blog.Blogwebsiterestapi.service.impl;

import com.springboot.blog.Blogwebsiterestapi.entity.Category;
import com.springboot.blog.Blogwebsiterestapi.entity.Post;
import com.springboot.blog.Blogwebsiterestapi.exception.ResourceNotFoundException;
import com.springboot.blog.Blogwebsiterestapi.payload.PostDto;
import com.springboot.blog.Blogwebsiterestapi.payload.PostResponse;
import com.springboot.blog.Blogwebsiterestapi.repository.CreateRepository;
import com.springboot.blog.Blogwebsiterestapi.repository.PostRepository;
import com.springboot.blog.Blogwebsiterestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    //constructor based dependency injection
    private PostRepository postRepository;
    private CreateRepository createRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper,CreateRepository createRepository) {
        this.postRepository = postRepository;
        this.mapper=mapper;
        this.createRepository=createRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to  entity
        Post post=mapToEntity(postDto);
        Category category=createRepository.findById(postDto.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Categoty","id", postDto.getCategoryId()));
       post.setCategory(category);
        Post newPost=postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse=mapToDto(newPost);

        return postResponse;

    }
    private Post mapToEntity(PostDto postDto){
        Post post=mapper.map(postDto,Post.class);
//        Post post=new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapToDto(Post post){
        PostDto postResponse=mapper.map(post,PostDto.class);
//        PostDto postResponse=new PostDto();
//        postResponse.setId(post.getId());
//        postResponse.setTitle(post.getTitle());
//        postResponse.setDescription(post.getDescription());
//        postResponse.setContent(post.getContent());
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
        PageRequest pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts=postRepository.findAll(pageable);
        List<Post> listOfPosts=posts.getContent();
        List<PostDto> content= posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
         Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        Category category=createRepository.findById(postDto.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Categoty","id", postDto.getCategoryId()));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
       post.setCategory(category);

        Post newUpdatedPost= postRepository.save(post);
        return mapToDto(newUpdatedPost);
    }

    @Override
    public void deletePostBYId(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategoryId(Long categoryId) {
        Category category=createRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Categoty","id", categoryId));
        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map((post)->mapToDto(post)).collect(Collectors.toList());
    }


}
