package com.riwi.examen_Rigo.infrastructure.adtract_services;

import com.riwi.examen_Rigo.api.dto.request.SurveyReq;
import com.riwi.examen_Rigo.api.dto.response.SurveyResp;


public interface ISurveyService extends CrudService<SurveyReq, SurveyResp, Long> {
    
    public String FIELD_BY_SORT = "creationDate";
}
