package com.tastyfoodwebapplication;

import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.repositories.CartRepository;
import com.tastyfoodwebapplication.repositories.UserRepository;
import com.tastyfoodwebapplication.services.UserService;
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
	CartService cartService;
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

		Optional<User> user = userRepository.findById("username");
		User realUser = user.get();

		System.out.println(realUser.getName());
		System.out.println(cartRepository.findById(realUser.getUsername()).get());
	}
}
