package com.emreilgar.utility;

import com.emreilgar.exception.AuthMicroserviceException;
import com.emreilgar.exception.ErrorType;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String createToken(Long userid){
        String token = "Brr:"+userid;
        return token;
    }

    public Long decodeToken(String token){
        try{
            String userid = token.split(":")[1];
            return Long.parseLong(userid);
        }catch (Exception e){
            throw new AuthMicroserviceException(ErrorType.INTERNAL_ERROR);
        }
    }

}
