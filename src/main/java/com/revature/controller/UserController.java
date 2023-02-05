package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.ResourceNotFoundException;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;


public class UserController implements HttpHandler {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public UserController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.userService = new UserService();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        String queryParams = exchange.getRequestURI().getQuery();
        String json = RequestParser.convertInputStreamToString(exchange.getRequestBody());
        String response = "Method not allowed.";

        try {
            switch (exchange.getRequestMethod()) {
                case "GET":
                    if (queryParams == null) {
                        response = objectMapper.writeValueAsString(userService.getAllUsers());
                    } else {
                        int id = Integer.parseInt(queryParams.split("=")[1]);
                        User user = userService.getUserById(id);
                        if(user == null) {
                            response = "No user found with id: " + id;
                            exchange.sendResponseHeaders(404, response.length());
                            break;
                        }
                        response = objectMapper.writeValueAsString(user);
                    }
                    exchange.sendResponseHeaders(200, response.length());
                    break;
                case "POST":
                    if(json.length() == 0) throw new RuntimeException("Request body must not by empty.");
                    User user = objectMapper.readValue(json, User.class);
                    response = objectMapper.writeValueAsString(userService.createUser(user));
                    exchange.sendResponseHeaders(201, response.length());
                    break;
                case "PUT":
                case "PATCH":
                    if(queryParams == null) throw new RuntimeException("Id must be provided as parameter.");
                    if(json.length() == 0) throw new RuntimeException("Request body must not by empty.");
                    int id = Integer.parseInt(queryParams.split("=")[1]);
                    user = objectMapper.readValue(json, User.class);
                    response = objectMapper.writeValueAsString(userService.updateUserById(user, id));
                    exchange.sendResponseHeaders(202, response.length());
                    break;
                case "DELETE":
                    if(queryParams == null) throw new RuntimeException("Id must be provided as parameter.");
                    id = Integer.parseInt(queryParams.split("=")[1]);
                    response = objectMapper.writeValueAsString(userService.deleteUserById(id));
                    exchange.sendResponseHeaders(202, response.length());
                    break;
                default:
                    exchange.sendResponseHeaders(405, response.length());
            }
        } catch(ResourceNotFoundException e){
            response = e.getMessage();
            exchange.sendResponseHeaders(404, response.length());
        } catch(Exception e){
            response = e.getMessage();
            log.error(response);
            exchange.sendResponseHeaders(400, response.length());
        }
        finally {
            exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
            exchange.close();
        }
    }
}
