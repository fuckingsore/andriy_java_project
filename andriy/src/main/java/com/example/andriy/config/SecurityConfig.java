package com.example.andriy.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    public static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {
        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/userPage");

        mapConfig.put(ROLE_USER, urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/adminMenu");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllRoles(){
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
