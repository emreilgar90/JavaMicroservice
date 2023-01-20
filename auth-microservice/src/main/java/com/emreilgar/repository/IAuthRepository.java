package com.emreilgar.repository;

import com.emreilgar.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findOptionalByUsernameAndPassword(String user,String password);

    Optional<Auth> findOptionalByUsername(String username);
}
