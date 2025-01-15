package com.spring.backend;

import com.spring.backend.models.UserProfile;
import com.spring.backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	private UserProfileRepository userProfileRepository;




	@Autowired
	public void  userProfileRepository(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}



	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {


		var profile = new UserProfile();

		profile.setName("Phenius Muthomi");
		profile.setBalance(12000.00);
		profile.setType("ADMIN");
		profile.setDescription("Hello, this is an example description added");
		userProfileRepository.save(profile);





		var profile2 = new UserProfile();

		profile2.setName("Amelina Vutagwa");
		profile2.setBalance(70000.00);
		profile2.setType("ADMIN");
		profile2.setDescription("Hello, this is an example description added");
		userProfileRepository.save(profile2);



	}





}
