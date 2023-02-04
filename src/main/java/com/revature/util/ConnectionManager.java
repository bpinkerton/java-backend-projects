package com.revature.util;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final ConnectionConfig config;

    static {
        Yaml yaml = new Yaml(new Constructor(ConnectionConfig.class));
        InputStream inputStream = ConnectionManager.class
                .getClassLoader()
                .getResourceAsStream("connection_config.yml");
        config = yaml.load(inputStream);
    }
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(config.url, config.user, config.password);
        }

        return connection;
    }

    private static class ConnectionConfig {
        public String url;
        public String user;
        public String password;
    }

}


