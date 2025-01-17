package com.spring.backend;

import com.spring.backend.models.UserProfile;
import com.spring.backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


}
