package com.vaccin.vaccin;

import com.vaccin.vaccin.model.Role;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.repository.RoleRepository;
import com.vaccin.vaccin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Date;

@SpringBootApplication
public class VaccinApplication  implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(VaccinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(roleRepository.findByRole("ROLE_ADMIN").isPresent())
			return;

		Role roleAdmin = new Role("ROLE_ADMIN");
		Role roleDoctor = new Role("ROLE_DOCTOR");
		Role roleUser = new Role("ROLE_USER");

		roleRepository.save(roleAdmin);
		roleRepository.save(roleDoctor);
		roleRepository.save(roleUser);

		User admin = new User();
		admin.setEmail("admin@vacc.ro");
		admin.setAge(400);
		admin.setAddress("Palatul Cotroceni");
		admin.setName("Admin");
		admin.setPassword(BCrypt.hashpw("Abcd_1234", BCrypt.gensalt()));
		admin.setRole(roleAdmin);

		userRepository.save(admin);

	}
}
