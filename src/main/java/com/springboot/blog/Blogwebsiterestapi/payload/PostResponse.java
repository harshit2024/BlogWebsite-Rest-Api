package com.springboot.blog.Blogwebsiterestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int PageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
