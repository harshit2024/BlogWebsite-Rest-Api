package com.springboot.blog.Blogwebsiterestapi;

import com.springboot.blog.Blogwebsiterestapi.entity.Role;
import com.springboot.blog.Blogwebsiterestapi.repository.RoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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

BlogWebsiteRestApiApplication implements CommandLineRunner{

   @Bean
   public ModelMapper modelMapper(){
	   return new ModelMapper();
   }
	public static void main(String[] args) {
		SpringApplication.run(BlogWebsiteRestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
	     Role adminRole=new Role();
		 adminRole.setName("ROLE_ADMIN");
		 roleRepository.save(adminRole);

		Role userRole=new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);


	}
}
