package com.riwi.examen_Rigo.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Email(message = "El email no es válido")
    @Size(
        min = 5, 
        max = 100,
        message = "El email debe tener entre 5 y 100 caracteres"
    )
    private String email;

    @NotBlank(message = "La contraseña es requerido")
    @Size(
        min = 8, 
        message = "la contraseña debe tener mas de 8 caracteres"
    )
    private String password;

    @NotNull(message = "EL campo no puedo ser nulo")
    private Boolean active;

}
