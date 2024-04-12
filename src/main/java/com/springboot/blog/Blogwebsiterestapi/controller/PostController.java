package com.springboot.blog.Blogwebsiterestapi.controller;


import com.springboot.blog.Blogwebsiterestapi.entity.Post;
import com.springboot.blog.Blogwebsiterestapi.payload.PostDto;
import com.springboot.blog.Blogwebsiterestapi.payload.PostResponse;
import com.springboot.blog.Blogwebsiterestapi.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post POST api


    @SecurityRequirement(
            name = "Bear Authentication"
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0",required=false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "20",required = false) int pageSize,
                                    @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy ,
                                    @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return  ResponseEntity.ok(postService.getPostById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id){
        PostDto newResponse=postService.updatePost(postDto,id);
        return  ResponseEntity.ok(newResponse);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
          postService.deletePostBYId(id);
          return new ResponseEntity<>("Post Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostById(@PathVariable ("id") Long categoryId){
        List<PostDto> posts=postService.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }

}
