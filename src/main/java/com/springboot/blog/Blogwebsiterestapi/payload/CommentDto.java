package com.springboot.blog.Blogwebsiterestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;


    @NotEmpty
    @Size(min =2,message = "Name should have atleast 2 characters")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min =10,message = "Body should have atleast 10 character")
    private String body;
}
