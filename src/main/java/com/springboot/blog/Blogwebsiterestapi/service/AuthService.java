package com.springboot.blog.Blogwebsiterestapi.service;

import com.springboot.blog.Blogwebsiterestapi.payload.LoginDto;
import com.springboot.blog.Blogwebsiterestapi.payload.RegisterDto;
import org.springframework.stereotype.Service;


public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
