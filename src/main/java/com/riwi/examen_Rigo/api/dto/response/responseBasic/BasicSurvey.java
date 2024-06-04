package com.riwi.examen_Rigo.api.dto.response.responseBasic;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicSurvey {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private Boolean active;
    
}
