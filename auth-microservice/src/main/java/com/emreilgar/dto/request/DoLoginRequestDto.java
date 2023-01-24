package com.emreilgar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoLoginRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilemez")
    @Size(min = 3,max = 32)
    String username;
    @NotBlank(message = "Kullanıcı şifresi boş geçilemez")
    @Size(min=8,max=64)
    String password;

}
