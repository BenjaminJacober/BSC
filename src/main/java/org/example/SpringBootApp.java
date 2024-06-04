package org.example;

import org.example.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan("org.example.*")
@EnableJpaRepositories(basePackages = {"org.example.repositories"})
@EntityScan(basePackages = "org.example.entities")
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class);
	}

	@Bean
	public CommandLineRunner run(UserRepository repository) {
		return (args -> {
			something(repository);
			System.out.println(repository.findAll());
		});
	}

	private void something(UserRepository BSCUserRepository) {
//        BSCUserRepository.save(new BSC_User("myName", "asdfas", "myEmail"));
	}

}