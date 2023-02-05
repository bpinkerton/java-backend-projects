package com.revature.util;

import java.io.IOException;
import java.io.InputStream;


public class RequestParser {
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();

        for(int ch; (ch = inputStream.read()) != -1;) sb.append((char) ch);

        return sb.toString();
    }
}
