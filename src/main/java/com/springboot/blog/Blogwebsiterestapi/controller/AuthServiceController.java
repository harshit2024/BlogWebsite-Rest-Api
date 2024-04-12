package com.springboot.blog.Blogwebsiterestapi.controller;


import com.springboot.blog.Blogwebsiterestapi.payload.JwtAuthResponse;
import com.springboot.blog.Blogwebsiterestapi.payload.LoginDto;
import com.springboot.blog.Blogwebsiterestapi.payload.RegisterDto;
import com.springboot.blog.Blogwebsiterestapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthServiceController {

    private AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse =new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return ResponseEntity.ok(response);
    }
}
