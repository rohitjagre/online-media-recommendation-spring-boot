package com.app.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.user.pojos.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserUtils {

    public static BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();

    private static Map<Integer, Token> tokenStore = new HashMap<>();

    public static String getAuthKey(User user) {
        String toGetToken = user.getEmail() + ":" + user.getPassword();
        String authString = passwordEncode.encode(toGetToken);
        System.out.println("Auth String " + authString);
        return authString;
    }

    public static Token parseToken(String Authorization) throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(Authorization.toString(), Token.class);
    }

    public static User parseUserJson(String userJson) throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(userJson, User.class);
    }

    public static Token getToken(User user) {
        if (tokenStore.containsKey(user.getId())) {
            System.out.println("token already here " + user.getId());
            return tokenStore.get(user.getId());
        }
        System.out.println("new token");
        Token token = new Token(getAuthKey(user), user.getId());
        tokenStore.put(user.getId(), token);
        System.out.println("added token " + tokenStore);
        return token;
    }

    public static boolean validateToken(Token token) {
        System.out.println(tokenStore);
        System.out.println("in token validation");
        System.out.println("Token received " + token);
        System.out.println("corresponding token " + tokenStore.get(token.getId()));
        return tokenStore.get(token.getId()).equals(token);
    }

    public static void removeToken(Token token) {
        System.out.println("before token removal " + tokenStore);
        tokenStore.remove(token.getId());
        System.out.println("after token removal " + tokenStore);
    }
}
