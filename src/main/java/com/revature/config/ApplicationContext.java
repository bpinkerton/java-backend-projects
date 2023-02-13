package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.HomeController;
import com.revature.controller.UserController;
import com.revature.repository.UserRepository;

import com.revature.service.UserService;

public class ApplicationContext {


    // Repository
    private final UserRepository userRepository;

    // Service
    private final UserService userService;

    // Controller
    private final HomeController homeController;
    private final UserController userController;

    // Util
    private final ObjectMapper objectMapper;

    ApplicationContext() {
        objectMapper = new ObjectMapper();

        userRepository = new UserRepository();

        userService = new UserService(userRepository);

        homeController = new HomeController();
        userController = new UserController(objectMapper, userService);

    }

    public ObjectMapper getObjectMapper(){
        return this.objectMapper;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public UserController getUserController() {
        return userController;
    }
}
