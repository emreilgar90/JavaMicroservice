package com.emreilgar.manager;

import com.emreilgar.dto.request.CreateProfileRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**isimlendirme benzersiz olmalıdır.*/
@FeignClient(name = "user-profile-manager"
        /**UserProfileMicroservice'inde ki controllerdan yolu kopyaladık /v1/dev/userprofile*/
        ,url = "${myapplication.user.feignurl}" //application.yml den parametreyi alacak
        ,decode404 = true)
/**UserProfile microservice'in de ki controller paketinin UserProfileController'ına denk geliyor.*/
public interface IUserProfileManager {

    @PostMapping("/createprofile") //metoda postMapping ekle
    public ResponseEntity<Boolean> createProfile(@RequestBody @Valid CreateProfileRequestDto dto);
    /**Yukarıda ki metodu Userprofile Controllerında ki CreatePofile dan gövdesi şekilde kopyaladık*/
    /**Sonrasında CreateProfileDto ya ulaşamadığımız için UserProfile Microservice'ine gidip CreateProfileDto'yu kopyalayıp kendi
     * microservice yapımızın içine kopyalayıp importladık ! Artık FeignClient isteği atabilir. */
}
