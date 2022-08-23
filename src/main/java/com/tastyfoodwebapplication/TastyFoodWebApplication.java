package com.tastyfoodwebapplication;

import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.repositories.CartRepository;
import com.tastyfoodwebapplication.repositories.UserRepository;
import com.tastyfoodwebapplication.services.UserService;
import com.tastyfoodwebapplication.utilities.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class TastyFoodWebApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(TastyFoodWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.addUser(new UserBinding("username", "password", "Dat", "BD", "113"));
		System.out.println(userRepository.count());

		User user = new SearchHelper<User>(userRepository.findAll()).find(anyUser -> anyUser.getUsername().equals("username"));

		System.out.println(user.getName());
		System.out.println(cartRepository.findById(user.getId()).get());
	}
}
