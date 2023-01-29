package com.emreilgar.controller;

import com.emreilgar.dto.request.DoLoginRequestDto;
import com.emreilgar.dto.request.RegisterRequestDto;
import com.emreilgar.dto.response.RegisterResponseDto;
import com.emreilgar.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.emreilgar.constants.RestApi.*;

@RestController
@RequestMapping("AUTH")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    /*Value anatasyonu application.yml ye gidiyor buuygulama.birdeger varsa o değeri alıyor. */
    @Value("${buuygulama.birdeger}")
    private String BuradaYMLdanDegerAlalim;
    /**
     * Bir end point e istek atarken farklı yollarla parametere gönderilebilir.
     * 1- Header, başlık içinde
     * 2- Body, form elementi içinde
     * burada bıdy içinde parametereleri almak daha güvenlidir.
     * @Valid
     * girilen bilgilerin belli kriterleri olmalıdır. mesela şifrenin karmaşıklığı
     * email adresinin doğru formatta olması v.s.
     * @param dto
     * @return
     */
    @PostMapping(DOLOGIN) //giriş yap       //@RequestBody ve @Valid Dto'ya çevirip bilgileri gizlediğimiz için.
    public ResponseEntity<Boolean> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        System.out.println("Gelen değer....." + BuradaYMLdanDegerAlalim);
        return ResponseEntity.ok(true);            //springboot valid mvn repositoryden dependencies'e ekle
    }


    @PostMapping(REGISTER)  //kayıt ol
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
       // authService.save(dto) ;
        System.out.println(BuradaYMLdanDegerAlalim);
        return ResponseEntity.ok(authService.save(dto));
    }


}
