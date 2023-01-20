package com.emreilgar.service;

import com.emreilgar.dto.request.RegisterRequestDto;
import com.emreilgar.dto.response.RegisterResponseDto;
import com.emreilgar.exception.AuthMicroserviceException;
import com.emreilgar.exception.ErrorType;
import com.emreilgar.mapper.IAuthMapper;
import com.emreilgar.repository.IAuthRepository;
import com.emreilgar.repository.entity.Auth;
import com.emreilgar.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    public  AuthService(IAuthRepository repository){
        super(repository);
        this.repository=repository;
    }

    public RegisterResponseDto save(RegisterRequestDto dto){     //kayıt olmak için (registerRequestDto dto) istek geliyor
        if (!dto.getPassword().equals(dto.getPassword()))
            throw new AuthMicroserviceException(ErrorType.REGISTER_REPASSWORD_ERROR);
        /* el ile auth a ileyip kaydediyorduk, bunun yerine mapper sayesinde bize verilen request dto'yu auth'a convert işlemini yapıyoruz.
        save(
                Auth.builder()
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .build()
        );*/
        if (repository.findOptionalByUsername(dto.getUsername()).isPresent())
            //Auth service e findOptionalByUserName olarak arattık eğer sisteme kayıtlı ise throw ile ErrorType dan hata fırlattık.
            throw new AuthMicroserviceException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
        Auth auth= save(IAuthMapper.INSTANCE.fromRegisterRequestDto(dto));
        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail()+ "ile başarılı şekilde kayıt oldunuz.");
        return result;

    }
}
