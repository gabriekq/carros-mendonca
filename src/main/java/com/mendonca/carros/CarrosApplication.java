package com.mendonca.carros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mendonca.carros.api.CarrosController;
import com.mendonca.carros.api.SwaggerConfig;
import com.mendonca.carros.domain.CarroService;
import com.mendonca.exception.ExceptionConfig;
import com.mendonca.security.SecurityConfig;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ExceptionConfig.class,CarrosController.class,CarroService.class,SecurityConfig.class})
public class CarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
	}

}
