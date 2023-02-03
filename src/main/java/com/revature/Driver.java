package com.revature;

import com.revature.config.HttpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Driver {

    private static final Logger log = LoggerFactory.getLogger(Driver.class);
    public static void main(String[] args) {
        try {
            HttpConfig.getHttpServer(8080).start();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
