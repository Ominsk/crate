package com.mini.crate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Crate API", version = "1.0", description = "Spring Boot project to deepen my knowledge"))
public class CrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrateApplication.class, args);
	}

}
