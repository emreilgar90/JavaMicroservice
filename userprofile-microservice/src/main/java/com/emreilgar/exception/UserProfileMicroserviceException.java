package com.emreilgar.exception;

import lombok.Getter;

@Getter
public class UserProfileMicroserviceException extends RuntimeException{
    private final ErrorType errorType;

    public UserProfileMicroserviceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }

    public UserProfileMicroserviceException(ErrorType errorType, String message){
        super(message);   //AUTH microservice'i için özelleştirilmiş hata, bizim hesaplamadığımız hatalar olabilir.

        this.errorType=errorType;
    }
}
