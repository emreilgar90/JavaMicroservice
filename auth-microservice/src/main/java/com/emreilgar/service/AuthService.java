package com.emreilgar.service;

import com.emreilgar.dto.request.CreateProfileRequestDto;
import com.emreilgar.dto.request.DoLoginRequestDto;
import com.emreilgar.dto.request.RegisterRequestDto;
import com.emreilgar.dto.response.RegisterResponseDto;
import com.emreilgar.exception.AuthMicroserviceException;
import com.emreilgar.exception.ErrorType;
import com.emreilgar.manager.IUserProfileManager;
import com.emreilgar.mapper.IAuthMapper;
import com.emreilgar.repository.IAuthRepository;
import com.emreilgar.repository.Auth;
import com.emreilgar.utility.ServiceManager;
import com.emreilgar.utility.TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    /**
     * DİKKAT !
     * kULLANMAK İSTEDİĞİN INTERFACE,SERVİCE,COMPONENT GİBİ SINIFLARDAN NESNE TÜRETMEK İÇİN 2 YOL VAR
     *
     * @AUTOWİRED İLE İŞARETLEMEK YA DA CONSTRUCTOR INJEKTİON İLE KULLANMAK 21.satır
     */
    private final IUserProfileManager userProfileManager;
    private final TokenGenerator tokenGenerator;

     /**constructor injection*/
    public AuthService(IAuthRepository repository, IUserProfileManager userProfileManager, TokenGenerator tokenGenerator) {
        super(repository);
        this.repository = repository;
        this.userProfileManager = userProfileManager;
        this.tokenGenerator = tokenGenerator;
    }

    public RegisterResponseDto save(RegisterRequestDto dto) {
        /**
         * Eğer şifre ile ikinci şifre uyuşmuyor ise, direkt false
         * dönülmesi mantıklıdır.
         */
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthMicroserviceException(ErrorType.REGISTER_REPASSWORD_ERROR);
        /**
         * Burada elle dönüşüm yerine Mapper kullanmak daha
         * doğru olacaktır.
         */
        /* Elle dönüşüm işlemi
        save(
                Auth.builder()
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .build()
        );
         */
        if (repository.findOptionalByUsername(dto.getUsername()).isPresent())
            throw new AuthMicroserviceException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);

        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterRequestDto(dto));
        userProfileManager.createProfile(CreateProfileRequestDto.builder()
                .token("")
                .authid(auth.getId())
                .username(auth.getUsername())
                .email(auth.getEmail())
                .build());
        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail()+" ile başarı şekilde kayıt oldunuz.");
        return  result;

    }
    public String doLogin(DoLoginRequestDto dto ){
       Optional<Auth>auth= repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
       if (auth.isEmpty())
           throw new AuthMicroserviceException(ErrorType.LOGIN_ERROR);
       /**lOGIN OLAN KİŞİLER İÇİN ÖZEL BİR TOKEN ÜRETMEK MANTIKLIDIR.*/
       /**Eğer yukarıda ki blok gerçekleşmediyse login başarılıdır token üretilip return edilir.*/
       return tokenGenerator.createToken(auth.get().getId());

    }
}