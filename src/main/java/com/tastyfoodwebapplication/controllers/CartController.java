package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.services.*;
import com.tastyfoodwebapplication.utilities.SearchHelper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class CartController {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private UserService userService;

    @Autowired
    public CartController(CartRepository cartRepository, ProductRepository productRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/cart")
    public String showCart(Model model, Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        Cart cart = userService.getCart(user);

        model.addAttribute("cart", cart);

        return "/cart";
    }

    @RequestMapping(value = "/cart/add-item/{productId}")
    public String addToCart(@PathVariable String productId, Authentication authentication) {
        Product product = productRepository.findById(productId).get();
        if (product != null) {
            User user = (User) authentication.getPrincipal();
            userService.addToCart(user, product, new HashSet<>());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/cart/delete-item/{cartItemId}")
    public String removeFromCart(@PathVariable String cartItemId, Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        userService.removeFromCart(user, cartItemId);

        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/update-cart-items")
    public String updateCart(@Valid @ModelAttribute Cart cart, BindingResult bindingResult, Authentication authentication) {
        if (cart.getCartItems() != null) {
            User user = userService.getLoggedInUser(authentication);
            Cart userCart = userService.getCart(user);

            cart.getCartItems().forEach(cartItem -> new SearchHelper<CartItem>(userCart.getCartItems()).find(cartItem1 -> cartItem1.getId().equals(cartItem.getId())).setQuantity(cartItem.getQuantity()));
            cartRepository.save(userCart);
        }

        return "redirect:/cart";
    }


}
