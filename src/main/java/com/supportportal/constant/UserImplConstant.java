package com.supportportal.constant;

import org.springframework.web.multipart.MultipartFile;

public class UserImplConstant {
    public static final String USERNAME_ALREADY_EXISTS = "Kullanıcı adı zaten var ";
    public static final String EMAIL_ALREADY_EXISTS = "Bu e-posta zaten var ";
    public static final String FOUND_USER_BY_USERNAME = "Bulunan kullanıcıyı kullanıcı adına göre döndürme: ";
    public static final String NO_USER_FOUND_BY_USERNAME = "Kullanıcı adına göre kullanıcı bulunamadı";
    public static final String NO_USER_FOUND_BY_EMAIL = "E-posta için kullanıcı bulunamadı:";
    public static final String PASSWORD_NOT_MATCH = "Eski Şifreniz Hatalıdır.";
    public static final String IS_NOT_AN_IMAGE_FILE_PLEASE_UPLOAD_AN_IMAGE = "bir resim dosyası değildir. Lütfen bir resim yükleyin.";
    public static final String EMAIL_SENT = "Yeni şifre içeren bir e-posta şu adrese gönderildi:";
    public static final String PASSWORD_UPDATED = "Yeni Şifreniz  ";
    public static final String USER_DELETED_SUCCESSFULLY = "Kullanıcı başarıyla silindi";
    public static final int BYTE_STREAM_SIZE = 1024;
    public static final String FIRSTNAME = "Admin";
    public static final String LASTNAME = "IT";
    public static final String USERNAME = "adminIt";
    public static final String EMAIL = "emlak.ofisi.deploy@gmail.com";
    public static final String CONTACT = "05549885183";
    public static final Long AGENCY_ID = 1L;
    public static final String ROLE = "ROLE_SUPER_ADMIN";
    public static final boolean IS_ACTIVE = true;
    public static final boolean IS_NON_LOCKED = true;
    public static final MultipartFile PROFILE_IMAGE = null;

}
