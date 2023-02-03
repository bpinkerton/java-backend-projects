package com.revature.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HomeController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;

        switch(exchange.getRequestMethod()){
            case "GET": response = "Welcome to the Open Reservation Service";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
                break;
            default: response = "Method not allowed.";
                exchange.sendResponseHeaders(405, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}
