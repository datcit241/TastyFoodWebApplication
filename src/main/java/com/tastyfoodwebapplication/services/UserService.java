package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.*;
import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.*;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.utilities.*;
import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private MappingService mappingService;
    private PasswordAuthentication passwordAuthentication;

    public UserService() {};

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, MappingService mappingService, PasswordAuthentication passwordAuthentication) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.mappingService = mappingService;
        this.passwordAuthentication = passwordAuthentication;
    }

    public boolean addUser(UserBinding userBinding) {
        if (userRepository.findById(userBinding.getUsername()).isPresent()) {
            return false;
        }

        User user = mappingService.bindUser(userBinding);
        userRepository.save(user);

        Cart cart =  new Cart(user.getId());
        this.cartRepository.save(cart);

        return true;
    }

    public boolean addToCart(User user, Product product, Set<DetailedProductCategory> selectedCategories) {
        if (product.getStatus() != ProductStatus.Available) {
            return false;
        }

        int quantity = 1;

        System.out.println(user);
        Cart cart = cartRepository.findById(user.getId()).get();

        CartItem cartItem = new SearchHelper<CartItem>(cart.getCartItems()).find(anyCartItem -> {
            boolean isTheSameProduct = anyCartItem.getProduct().equals(product);
            boolean isTheSameCategories = selectedCategories == null && anyCartItem.getSelectedCategories() == null || anyCartItem.getSelectedCategories().equals(selectedCategories);
            return isTheSameProduct && isTheSameCategories;
        });

        if (cartItem != null) {
            cartItem.incrementQuantity();
        } else {
            cartItem = new CartItem(product, quantity, selectedCategories);
            cart.addCartItem(cartItem);
        }

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return true;
    }

    public boolean removeFromCart(User user, String cartItemId) {
        Cart cart = cartRepository.findById(user.getUsername()).get();

        List<CartItem> cartItems = cart.getCartItems();
        int size = cartItems.size();

        CartItem cartItem = new SearchHelper<CartItem>(cartItems).find(anyCartItem -> anyCartItem.getId().equals(cartItemId));
        cartItemRepository.delete(cartItem);;

        cart.removeCartItem(cartItem);
        cartRepository.save(cart);

        return size - 1 == cartItems.size();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SearchHelper<User>(userRepository.findAll()).find(user -> user.getUsername().equals(username));
    }
}
