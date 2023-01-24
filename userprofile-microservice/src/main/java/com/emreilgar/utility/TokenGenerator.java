package com.emreilgar.utility;

import com.emreilgar.exception.UserProfileMicroserviceException;
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
            if (!token.split(":")[0].equals("Brr"))
                throw new UserProfileMicroserviceException(ErrorType.INVALID_TOKEN);
            String userid = token.split(":")[1];  //split ile : den ikiye ayrıyor
            return Long.parseLong(userid);
        }catch (Exception e){
            throw new UserProfileMicroserviceException(ErrorType.INVALID_TOKEN);
        }
    }

}
