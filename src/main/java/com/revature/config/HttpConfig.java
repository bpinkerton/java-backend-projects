package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.HomeController;
import com.revature.controller.UserController;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.httpserver.HttpServerImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;



public class HttpConfig {
    private static final Logger log = LoggerFactory.getLogger(HttpConfig.class);
    private static final Map<String, HttpHandler> routes = new HashMap<>();
    private static final ApplicationContext applicationContext;

    static {
        applicationContext = new ApplicationContext();

        routes.put("/", applicationContext.getHomeController());
        routes.put("/users", applicationContext.getUserController());
    }

    public static HttpServer getHttpServer(int port) throws IOException {
        log.info("Initializing HttpServer ...");
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        for(String route: routes.keySet()){
            try{
                server.createContext(route, routes.get(route));
                log.info("Binding route: {} to handler: {}", route, routes.get(route).getClass().getSimpleName());
            } catch(Exception e){
                log.error("Unable to bind route: {} to handler: {}",
                        route, routes.get(route).getClass().getSimpleName(), e);
            }
        }

        log.info("HttpServer ready to start on port: {}", port);

        return server;
    }
}