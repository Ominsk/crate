package com.mini.crate;

import com.mini.crate.domain.Role;
import com.mini.crate.domain.User;
import com.mini.crate.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.BeanProperty;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Crate API", version = "1.0", description = "Spring Boot project to deepen my knowledge"))
public class CrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrateApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService) {

		return args -> {

			userService.deleteAll();

			userService.saveRole(Role.builder().name("ROLE_USER").build());
			userService.saveRole(Role.builder().name("ROLE_MANAGER").build());
			userService.saveRole(Role.builder().name("ROLE_ADMIN").build());
			userService.saveRole(Role.builder().name("ROLE_SUPER_ADMIN").build());

			userService.save(User.builder().name("John Travolta").userName("john").password("1234").build());
			userService.save(User.builder().name("Will Smith").userName("will").password("abcd").build());
			userService.save(User.builder().name("Jim Carry").userName("jim").password("1234").build());
			userService.save(User.builder().name("Arnold Scwarzenegger").userName("arnold").password("1234").build());

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");

			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");


		};

	}

}
