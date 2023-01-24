package com.emreilgar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(5100,"Sunucuda beklenmeyen hata oluştu.",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(5200,"Geçersiz Token Bilgisi" , HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_REQUEST(5201,"Yetkisiz giriş denemesi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(4100,"Parametre eksik veya hatalı",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4110,"Kullanıcı adı ya da şifre hatalı",HttpStatus.BAD_REQUEST),
    REGISTER_REPASSWORD_ERROR(4111,"Girilen şifreler uyuşmuyor.",HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(4112,"Kullanıcı adı zaten kayıtlı" , HttpStatus.BAD_REQUEST);


    //ERROR TYPE'IN CONS'LARINI YAZDIM
    int code;
    String message;
    HttpStatus httpStatus;
}
