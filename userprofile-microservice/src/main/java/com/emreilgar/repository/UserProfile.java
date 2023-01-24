package com.emreilgar.repository;

import com.emreilgar.utility.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "tbluserprofile")
@Entity
public class UserProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Auth Microservice de kayıt olan bir kullanıcının id bilgisini tutar.
     * Yani birisi Register veyahut Login olmak isterse UserProfileDb'ine bilgi vermek amaçlı.*/

    Long authid;
    String username;
    String email;
    String phone;
    String address;
    String profileimage;
    String avatar;
    String info;
    String facebook;
    String twitter;
    String instagram;

}
