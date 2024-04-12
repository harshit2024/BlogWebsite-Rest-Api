package com.springboot.blog.Blogwebsiterestapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info =@Info(
				title = "Spring Boot Blog App Rest APIs",
				description = "Spring Boot Blog App Rest API's Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Harshit",
						email = "Harshitgsrivastava07@gmail.com"

				)
		)
)
public class

BlogWebsiteRestApiApplication {

   @Bean
   public ModelMapper modelMapper(){
	   return new ModelMapper();
   }
	public static void main(String[] args) {
		SpringApplication.run(BlogWebsiteRestApiApplication.class, args);
	}
}
