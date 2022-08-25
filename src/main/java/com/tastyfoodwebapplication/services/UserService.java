package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.*;
import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.*;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.utilities.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.security.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private MappingService mappingService;
    private PasswordAuthentication passwordAuthentication;

    public UserService() {};

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderHistoryRepository orderHistoryRepository, OrderRepository orderRepository, ProductRepository productRepository, MappingService mappingService, PasswordAuthentication passwordAuthentication) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.mappingService = mappingService;
        this.passwordAuthentication = passwordAuthentication;
    }

    public boolean addUser(UserBinding userBinding) {
        if (new SearchHelper<User>(userRepository.findAll()).find(user -> user.getUsername().equals(userBinding.getUsername())) != null) {
            return false;
        }

        User user = mappingService.bindUser(userBinding);
        userRepository.save(user);

        Cart cart =  new Cart(user.getId());
        this.cartRepository.save(cart);

        OrderHistory orderHistory = new OrderHistory(user.getId());
        this.orderHistoryRepository.save(orderHistory);

        return true;
    }

    public Cart getCart(User user) {
        return user == null ? null : cartRepository.findById(user.getId()).get();
    }

    public boolean addToCart(User user, Product product, Set<DetailedProductCategory> selectedCategories) {
        if (product.getStatus() != ProductStatus.Available) {
            return false;
        }

        int quantity = 1;

        Cart cart = getCart(user);

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

//        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return true;
    }

    public boolean removeFromCart(User user, String cartItemId) {
        Cart cart = getCart(user);

        List<CartItem> cartItems = cart.getCartItems();
        int size = cartItems.size();

        CartItem cartItem = new SearchHelper<CartItem>(cartItems).find(anyCartItem -> anyCartItem.getId().equals(cartItemId));
//        cartItemRepository.delete(cartItem);;

        cart.removeCartItem(cartItem);
        cartRepository.save(cart);

        return size - 1 == cartItems.size();
    }

    public void takeOrder(User user, List<CartItem> cartItems) {
        double totalPrice = 0d;

        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getQuantity() * (cartItem.getProduct().getPrice() * cartItem.getProduct().getDiscount());

            if (cartItem.getSelectedCategories().size() != 0) {
                for (DetailedProductCategory e : cartItem.getSelectedCategories()) {
                    totalPrice += e.getCharge();
                }
            }
        }

        Order order = new Order(user, cartItems, totalPrice, LocalDateTime.now());
        OrderHistory orderHistory = orderHistoryRepository.findById(user.getId()).get();
        orderHistory.addOrder(order);

        Cart cart = cartRepository.findById(user.getId()).get();
        cart.getCartItems().removeAll(cartItems);

        cartRepository.save(cart);
        orderHistoryRepository.save(orderHistory);
    }

    public List<Order> getOrders(User user) {
        return new SearchHelper<Order>(orderRepository.findAll()).get(order -> order.getUser().equals(user), new OrderByRecentnessComparator());
    }

    public List<Order> getOrdersInProgress(User user) {
        return new SearchHelper<Order>(orderRepository.findAll()).get(order -> order.getUser().equals(user) && order.getStatus().isInProgress(), new OrderByRecentnessComparator());
    }

    public User getLoggedInUser(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    public UserRole getRole(Principal principal) {
        if (principal != null) {
            boolean isUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.User.toString()));
            return isUser ? UserRole.User : UserRole.Admin;
        }

        return null;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SearchHelper<User>(userRepository.findAll()).find(user -> user.getUsername().equals(username));
    }
}
