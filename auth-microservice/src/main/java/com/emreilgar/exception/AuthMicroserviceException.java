package com.emreilgar.exception;

import lombok.Getter;

@Getter
public class AuthMicroserviceException extends RuntimeException{
    private final ErrorType errorType;

    public AuthMicroserviceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }

    public AuthMicroserviceException(ErrorType errorType,String message){
        super(message);   //AUTH microservice'i için özelleştirilmiş hata, bizim hesaplamadığımız hatalar olabilir.

        this.errorType=errorType;
    }
}
