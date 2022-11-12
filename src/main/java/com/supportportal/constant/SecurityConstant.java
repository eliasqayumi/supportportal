package com.supportportal.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 21_600_000; // 6 hours expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Jeton doğrulanamıyor";
    public static final String GET_ARRAYS_LLC = "TÜRK EMLAK OFİSİ";
    public static final String GET_ARRAYS_ADMINISTRATION = "Emlak Yönetim Portalı";
    public static final String AUTHORITIES = "yetkililer";
    public static final String FORBIDDEN_MESSAGE = "Bu sayfaya erişmek için giriş yapmanız gerekiyor";
    public static final String ACCESS_DENIED_MESSAGE = "Bu sayfaya erişme izniniz bulunmuyor";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = { "/user/login", "/user/reset-password/**","/user/image/**"};
}