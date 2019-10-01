package com.springbeer.beerproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@EntityScan(basePackages = {"com.springbeer.beerproject"})
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class BeerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerProjectApplication.class, args);
	}

}
