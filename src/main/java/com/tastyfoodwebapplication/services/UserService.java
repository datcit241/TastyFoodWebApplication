package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.*;
import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.bindings.*;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.utilities.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.function.*;

@Service
public class UserService {
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private MappingService mappingService;
    private PasswordAuthentication passwordAuthentication;

    public UserService() {};

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, MappingService mappingService, PasswordAuthentication passwordAuthentication) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.mappingService = mappingService;
        this.passwordAuthentication = passwordAuthentication;
    }

    public boolean addUser(UserBinding userBinding) {
        if (userRepository.findById(userBinding.getUsername()).isPresent()) {
            return false;
        }

        User user = mappingService.bindUser(userBinding);
        userRepository.save(user);

        Cart cart =  new Cart(user.getUsername());
        this.cartRepository.save(cart);

        return true;
    }

    public boolean addToCart(User user, Product product, Set<ProductCategory> selectedCategories) {
        if (product.getStatus() != ProductStatus.Available) {
            return false;
        }

        int quantity = 1;

        Cart cart = cartRepository.findById(user.getUsername()).get();

        CartItem cartItem = new SearchHelper<CartItem>(cart.getCartItems()).find(anyCartItem -> anyCartItem.getProduct().equals(product) && anyCartItem.getSelectedCategories().equals(selectedCategories));

        if (cartItem != null) {
            cartItem.incrementQuantity();
        } else {
            cartItem = new CartItem(product, quantity, selectedCategories);
            cart.addCartItem(cartItem);
        }

        cartRepository.save(cart);

        return true;
    }

    public boolean removeFromCart(User user, String cartItemId) {
        Cart cart = cartRepository.findById(user.getUsername()).get();

        List<CartItem> cartItems = cart.getCartItems();
        int size = cartItems.size();

        CartItem cartItem = new SearchHelper<CartItem>(cartItems).find(anyCartItem -> anyCartItem.getId().equals(cartItemId));
        cart.removeCartItem(cartItem);

        return size - 1 == cartItems.size();
    }

}
