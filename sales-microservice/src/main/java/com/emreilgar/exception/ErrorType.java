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
    BAD_REQUEST_ERROR(4300,"Parametre eksik veya hatalı",HttpStatus.BAD_REQUEST);



    //ERROR TYPE'IN CONS'LARINI YAZDIM
    int code;
    String message;
    HttpStatus httpStatus;
}
