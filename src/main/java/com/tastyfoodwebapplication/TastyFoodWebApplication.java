package com.tastyfoodwebapplication;

import com.tastyfoodwebapplication.enums.UserRole;
import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.repositories.CartRepository;
import com.tastyfoodwebapplication.repositories.UserRepository;
import com.tastyfoodwebapplication.services.ShopOwnerService;
import com.tastyfoodwebapplication.services.UserService;
import com.tastyfoodwebapplication.utilities.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TastyFoodWebApplication implements CommandLineRunner {
	private ShopOwnerService shopOwnerService;
	private UserService userService;
	private UserRepository userRepository;
	private CartRepository cartRepository;


	@Autowired
	public TastyFoodWebApplication(ShopOwnerService shopOwnerService, UserService userService, UserRepository userRepository, CartRepository cartRepository) {
		this.shopOwnerService = shopOwnerService;
		this.userService = userService;
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TastyFoodWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.addUser(new UserBinding("Dat", "username", "password", "", "dat@mail", "BD", "113"));
		userService.addUser(new UserBinding("Dat", "admin", "admin", "", "admin@tasty.vn", "BD", "113"));
		System.out.println(userRepository.count());

		User user = new SearchHelper<User>(userRepository.findAll()).find(anyUser -> anyUser.getUsername().equals("username"));
		User admin = new SearchHelper<User>(userRepository.findAll()).find(anyUser -> anyUser.getUsername().equals("admin"));
		admin.setRole(UserRole.Admin);

		System.out.println(cartRepository.count());

		shopOwnerService.addProduct("Barbecue salad", "Delicious dish", "/images/plate1.png", 22d);
		shopOwnerService.addProduct("Salad with fish", "Delicious dish", "/images/plate2.png", 12d);
		shopOwnerService.addProduct("Spinach salad", "Delicious dish", "/images/plate3.png", 9.5d);
//		shopOwnerService.addProduct("Caridea Prawn Lobster Crab Seafood", "Delicious dish", "/images/plate4.png", 50d);
//		shopOwnerService.addProduct("Roast chicken Barbecue", "Delicious dish", "/images/plate5.png", 25d);
	}
}
