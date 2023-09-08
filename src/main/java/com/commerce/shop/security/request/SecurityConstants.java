package com.commerce.shop.security.request;

public class SecurityConstants {

    //jwt
    public static final String SECRET_KEY ="xczcADBVJLKCNVKBAAxczxcmapok23mksg043k90jfjmvuj2409uijfiap01238j";
    public static final int TOKEN_EXPIRATION =7200000;

    public static final String AUTHORIZATION ="Authorization";
    public static final String BEARER ="Bearer ";


    //Authentication mapping
    public static final String AUTH_MAPPING ="/auth";
    public static final String LOGIN_URL ="/login";

    //Category mapping
    public static final String CATEGORY_MAPPING ="/category";

    //Product mapping
    public static final String PRODUCT_MAPPING ="/product";
}
