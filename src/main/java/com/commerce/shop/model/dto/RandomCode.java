package com.commerce.shop.model.dto;

import java.security.SecureRandom;

public class RandomCode {


    private static final String RANDOM_CHARACTER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static String getRandomString(int length) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_CHARACTER.charAt(rnd.nextInt(RANDOM_CHARACTER.length())));
        }
        return sb.toString();
    }
}
