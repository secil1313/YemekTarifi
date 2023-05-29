package com.secil.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4100, "Kullancı adı veya şifre hatalı", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(4200, "Şifreler aynı değil", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4300, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    ACTIVATE_CODE_ERROR(4500, "Aktivasyon kod hatası", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4600,"Token hatası" ,  HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4700, "Token oluşturulamadı", HttpStatus.BAD_REQUEST),
    ADRESS_NOT_FOUND(4800, "Böyle bir adres bulunamadı", HttpStatus.NOT_FOUND),
    NOT_ADMIN(4900, "Admin olmadığınız için ekleme yapamazsınız", HttpStatus.NOT_FOUND),
    EXIST_CATEGORY(5000, "Bu kategori zaten bulunmaktadır.", HttpStatus.NOT_FOUND),
    EXIST_RECIPE(5200, "Bu tarif zaten bulunmaktadır.", HttpStatus.NOT_FOUND),
    RECIPE_NOT_FOUND(5300, "Tarif bulunamadı.", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND(5400, "Yorum bulunamadı.", HttpStatus.NOT_FOUND),
    POINT_NOT_FOUND(5400, "Point bulunamadı.", HttpStatus.NOT_FOUND);


    private int code;
    private String message;
    HttpStatus httpStatus;
}
