package com.riwi.examen_Rigo.infrastructure.adtract_services;

import com.riwi.examen_Rigo.api.dto.request.UserReq;
import com.riwi.examen_Rigo.api.dto.response.UserResp;

public interface IUserService extends CrudService<UserReq, UserResp, Long>{
    
    public String FIELD_BY_SORT = "name";
}
