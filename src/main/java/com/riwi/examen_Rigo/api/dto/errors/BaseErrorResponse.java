package com.riwi.examen_Rigo.api.dto.errors;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder//Super builder para activar el metodo del padre
public class BaseErrorResponse implements Serializable{
    
    private String status;
    private Integer code;
}
