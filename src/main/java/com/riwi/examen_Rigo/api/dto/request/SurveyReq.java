package com.riwi.examen_Rigo.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyReq {

    @NotBlank(message =  "El titulo es requrido")
    private String title;

    @NotBlank(message = "la descripcion es requerida")
    private String description;

    @NotNull(message = "La fecha y hora es requerida")
    @FutureOrPresent(message = "La fecha no puede ser del pasado")
    private LocalDateTime creationDate;

    @NotNull(message = "EL campo no puedo ser nulo")
    private Boolean active;

    @NotNull(message = "El id del cliente es obligatorio")
    private Long creador_id;




}
