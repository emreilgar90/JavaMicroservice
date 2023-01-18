package com.emreilgar.controller;

import com.emreilgar.dto.request.DoLoginRequestDto;
import com.emreilgar.dto.request.RegisterRequestDto;
import com.emreilgar.repository.entity.Auth;
import com.emreilgar.repository.entity.State;
import com.emreilgar.service.AuthService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping(DOLOGIN) //giriş yap       //@RequestBody ve @Valid Dto'ya çevirip bilgileri gizlediğimiz için.
    public ResponseEntity<Boolean> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(true);            //springboot valid mvn repositoryden dependencies'e ekle
    }

    @PostMapping(REGISTER)  //kayıt ol
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto dto){
        authService.save(dto) ; //
        return ResponseEntity.ok(authService.save(dto));
    }

}
