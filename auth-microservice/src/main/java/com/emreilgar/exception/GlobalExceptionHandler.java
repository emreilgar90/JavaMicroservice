package com.emreilgar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    //ErrorType larda oluşturduğum hataların dışında bir hataysa dönsün yoksa dönmesin.
    @ExceptionHandler(Exception.class) //runtimeexception'ı dinliyecek.
    @ResponseBody
    public ResponseEntity<String> handlerRuntimeException(RuntimeException exception){
        System.out.println("hata oluştu");
        return ResponseEntity.ok(exception.getMessage());
    }
    @ExceptionHandler(AuthMicroserviceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>handlerAuthMicroserviceException(AuthMicroserviceException exception){
        return new ResponseEntity<>(createErrorMessage(exception,exception.getErrorType()), HttpStatus.BAD_REQUEST);
    }
    /**
     * Hata yakalama işlemleri bir çok hata için ayrı ayrı yapılmalıdır. Bu nedenle tüm hataların
     * içerisine Log alma işlemi yazmak zorunda kalırız, bu işlemleri tekelleştirmek ve hata log kayıtlarını
     * toplamak için tek bir method kullanmak daha doğru olacaktır.
     * @param exception
     * @param errorType
     * @return */

    private ErrorMessage createErrorMessage(Exception exception,ErrorType errorType){
        System.out.println("Tüm hatalatın geçtiği nokta...:" + exception.getMessage());
        return ErrorMessage.builder()
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build();
    }

}
