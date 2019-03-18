package com.webucation.util;

import java.util.HashMap;
import java.util.Map;

public class CookieParse {
    static public Map<String, String> parse(String cookie) {
        String[] split = cookie.split(";");
        Map<String, String> cookieMap = new HashMap<>();
        for (String item : split) {
            String[] kvs = item.split("=");
            cookieMap.put(kvs[0], kvs[1]);
        }
        return cookieMap;
    }
}
