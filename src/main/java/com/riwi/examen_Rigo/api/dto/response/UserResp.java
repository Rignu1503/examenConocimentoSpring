package com.riwi.examen_Rigo.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {

    private Long id;
    private String name;
    private String email;
    private Boolean active;

    
}
