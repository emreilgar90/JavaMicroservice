package com.emreilgar.controller;

import com.emreilgar.dto.request.BaseRequestDto;
import com.emreilgar.dto.request.CreateProfileRequestDto;
import com.emreilgar.repository.UserProfile;
import com.emreilgar.service.UserProfileService;
import com.emreilgar.utility.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static com.emreilgar.constants.RestApis.*; //BU import'u restapis'i yazdıktan sonra import et

@RestController
@RequestMapping(USERPROFILE)//bu kısmı da class adını ekle
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final TokenGenerator tokenGenerator;
    @PostMapping(GETALL)
    public ResponseEntity<List<UserProfile>> userProfileList(@RequestBody @Valid BaseRequestDto dto){
        Long userid = tokenGenerator.decodeToken(dto.getToken());

        return ResponseEntity.ok(userProfileService.findAll());
    }

    /**@RequestBody ve @Valid anatasyonlarını unutma !*/

    @PostMapping(CREATEPROFILE) //metoda postMapping ekle
    public ResponseEntity<Boolean> createProfile(@RequestBody @Valid CreateProfileRequestDto dto){
        userProfileService.save(
                UserProfile.builder()
                        .authid(dto.getAuthid())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .build()
        );
        return ResponseEntity.ok(true);


    }
}
