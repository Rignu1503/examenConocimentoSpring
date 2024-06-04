package com.riwi.examen_Rigo.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.riwi.examen_Rigo.domain.entities.Question;
import com.riwi.examen_Rigo.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResp {
    
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private Boolean active;
    private List<Question> questions;
}
