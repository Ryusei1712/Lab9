package com.example.lab09.service;


import com.example.lab09.model.Order;
import com.example.lab09.model.Product;
import com.example.lab09.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DataInitialization {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostConstruct
    public void initializeData() {
        // Thêm mẫu dữ liệu cho Product
        productService.addProduct(new Product(1L, "TV set", "Samsung 1.0", 3000, "black", "Factory new"));
        productService.addProduct(new Product(2L, "TV set", "LG 24 inch", 4000, "grey", "Factory new"));
        productService.addProduct(new Product(3L, "TV set", "Samsung 2", 2000, "black", "Factory new"));
        productService.addProduct(new Product(4L, "TV set", "Samsung 4", 3000, "black", "Factory new"));
        productService.addProduct(new Product(5L, "TV set", "Samsung 6", 5000, "black", "Factory new"));
        productService.addProduct(new Product(6L, "TV set", "Samsung 7", 9000, "black", "Factory new"));

        // Thêm mẫu dữ liệu cho User
        userService.registerUser(new User(1L, "user1@gmail.com", "password1", "User", "One"));
        userService.registerUser(new User(2L, "user2@gmail.com", "password2", "User", "Two"));
        userService.registerUser(new User(3L, "user3@gmail.com", "password3", "User", "Three"));
        userService.registerUser(new User(4L, "user4@gmail.com", "password4", "User", "Four"));
        userService.registerUser(new User(5L, "user5@gmail.com", "password5", "User", "Five"));

        // Thêm mẫu dữ liệu cho Order
        orderService.addOrder(new Order(1L, "ORD001", 6000, userService.getUserById(1L), Arrays.asList(productService.getProductById(1L), productService.getProductById(2L))));
        orderService.addOrder(new Order(2L, "ORD002", 4000, userService.getUserById(2L), Arrays.asList(productService.getProductById(3L), productService.getProductById(4L))));
        orderService.addOrder(new Order(3L, "ORD003", 9000, userService.getUserById(3L), Arrays.asList(productService.getProductById(5L))));
        orderService.addOrder(new Order(4L, "ORD004", 6000, userService.getUserById(4L), Arrays.asList(productService.getProductById(1L), productService.getProductById(2L))));
        orderService.addOrder(new Order(5L, "ORD005", 3000, userService.getUserById(5L), Arrays.asList(productService.getProductById(3L))));
    }
}