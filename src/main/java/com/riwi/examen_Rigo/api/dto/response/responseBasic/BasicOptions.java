package com.riwi.examen_Rigo.api.dto.response.responseBasic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BasicOptions {
    
    private Long id;
    private String text;
    private Boolean active;
    
}
