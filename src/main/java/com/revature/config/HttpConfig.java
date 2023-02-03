package com.revature.config;

import com.revature.controller.HomeController;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;


public class HttpConfig{
    private static final Map<String, HttpHandler> routes = new HashMap<>();;

    static {
        routes.put("/", new HomeController());
    }
    public static HttpServer getHttpServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        for(String route: routes.keySet()){
            server.createContext(route, routes.get(route));
        }

        return server;
    }
}