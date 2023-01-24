package com.emreilgar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication   //Spring anatasyonunu ekle
@EnableFeignClients //diğer microservice'lerle iletişim için
public class AuthMicroServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthMicroServiceApplication.class);   //çağır çalıştır.
    }
}