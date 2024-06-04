package com.riwi.examen_Rigo.api.dto.response.responseBasic;

import com.riwi.examen_Rigo.util.enums.QuestionsOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicQuestion {
    
    private Long id;
    private String text;
    private QuestionsOption type;
    private Boolean active;
}
