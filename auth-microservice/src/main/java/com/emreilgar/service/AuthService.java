package com.emreilgar.service;

import com.emreilgar.dto.request.RegisterRequestDto;
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

    public Boolean save(RegisterRequestDto dto){
        if (!dto.getPassword().equals(dto.getPassword()))
            return false;
        save(
                Auth.builder()
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .build()
        );
        return true;
    }
}
