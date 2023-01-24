package com.emreilgar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {
    //UserProfile entity de ki authid ile eşleştirmek için
    Optional<UserProfile> findAllByAuthid(Long authid);

    Optional<UserProfile> findOptionalByAuthid(Long userid);


}
