package com.springboot.blog.Blogwebsiterestapi.service.impl;

import com.springboot.blog.Blogwebsiterestapi.entity.Role;
import com.springboot.blog.Blogwebsiterestapi.entity.User;
import com.springboot.blog.Blogwebsiterestapi.exception.BlogApiException;
import com.springboot.blog.Blogwebsiterestapi.payload.LoginDto;
import com.springboot.blog.Blogwebsiterestapi.payload.RegisterDto;
import com.springboot.blog.Blogwebsiterestapi.repository.RoleRepository;
import com.springboot.blog.Blogwebsiterestapi.repository.UserRepository;
import com.springboot.blog.Blogwebsiterestapi.security.JwtTokenProvider;
import com.springboot.blog.Blogwebsiterestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

   private AuthenticationManager authenticationManager;
   private UserRepository userRepository;
   private RoleRepository roleRepository;
   private PasswordEncoder passwordEncoder;
   private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository repository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository=userRepository;
        this.roleRepository=repository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;

    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.genrateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        //check for username in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw  new BlogApiException(HttpStatus.BAD_REQUEST,"This Username Already Exists");
        }
        //check for username in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw  new BlogApiException(HttpStatus.BAD_REQUEST,"This Email Already Exists");
        }

        User user=new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles=new HashSet<>();
        Role userRole=roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully";
    }


}
