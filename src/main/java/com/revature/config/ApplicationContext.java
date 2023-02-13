package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.HomeController;
import com.revature.controller.ProductController;
import com.revature.controller.UserController;
import com.revature.repository.ProductRepository;
import com.revature.repository.UserRepository;
import com.revature.service.ProductService;
import com.revature.service.UserService;

public class ApplicationContext {


    // Repository
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // Service
    private final UserService userService;
    private final ProductService productService;

    // Controller
    private final HomeController homeController;
    private final UserController userController;
    private final ProductController productController;

    // Util
    private final ObjectMapper objectMapper;

    ApplicationContext() {
        objectMapper = new ObjectMapper();

        userRepository = new UserRepository();
        productRepository = new ProductRepository();

        userService = new UserService(userRepository);
        productService = new ProductService(productRepository);

        homeController = new HomeController();
        userController = new UserController(objectMapper, userService);
        productController = new ProductController(objectMapper, productService);

    }

    public ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository(){
        return productRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public UserController getUserController() {
        return userController;
    }

    public ProductController getProductController() {
        return productController;
    }
}
