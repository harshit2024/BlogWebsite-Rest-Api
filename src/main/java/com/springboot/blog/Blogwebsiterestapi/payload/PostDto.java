package com.springboot.blog.Blogwebsiterestapi.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


import javax.xml.stream.events.Comment;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 3,message = "Title should have more than 2 character")
    private String title;

    @NotEmpty
    @Size(min = 11,message = "Description should have more than 10 character")
    private String description;

    @NotEmpty
    private String content ;
    private Set<CommentDto> comments;

    private  Long categoryId;


}
