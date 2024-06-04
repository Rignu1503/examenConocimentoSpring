package com.riwi.examen_Rigo.infrastructure.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.examen_Rigo.api.dto.request.SurveyReq;
import com.riwi.examen_Rigo.api.dto.response.SurveyResp;
import com.riwi.examen_Rigo.domain.entities.Question;
import com.riwi.examen_Rigo.domain.entities.Survey;
import com.riwi.examen_Rigo.domain.entities.User;
import com.riwi.examen_Rigo.domain.repositories.SurveyRepository;
import com.riwi.examen_Rigo.domain.repositories.UserRepository;
import com.riwi.examen_Rigo.infrastructure.adtract_services.EmailHelper;
import com.riwi.examen_Rigo.infrastructure.adtract_services.ISurveyService;
import com.riwi.examen_Rigo.util.enums.SortType;
import com.riwi.examen_Rigo.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService implements ISurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final EmailHelper emailHelper;

    @Override
    public SurveyResp create(SurveyReq request) {

        Survey survey = this.requestToEntity(request, new Survey());


        if(Objects.nonNull(survey.getCreator().getEmail())){
            this.emailHelper.sendMail(survey.getCreator().getEmail(), survey.getTitle(),  survey.getCreationDate());
        }


        return this.entityToResponse(this.surveyRepository.save(survey));

       

    }

    @Override
    public SurveyResp get(Long id) {
        
        return this.entityToResponse(this.find(id));
    }

    @Override
    public SurveyResp update(SurveyReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<SurveyResp> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;
 
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        
        return this.surveyRepository.findAll(pagination).map(this::entityToResponse);
    }

        

    private SurveyResp entityToResponse(Survey entity) {

       
        List<Question> questions = new ArrayList<>(); 

        return SurveyResp.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .creationDate(entity.getCreationDate())
        .active(entity.getActive())
        .questions(questions)
       
        .build();
    }

/*
    private List<QuestionResp> entityToQuestionResponse() {
       
        List<QuestionResp> questionResps = new ArrayList<>();
        for(Question entity: question){
            List<BasicOptions> basicOptionsQuestion = this.
        }


        return null;
    }
 */

    private Survey requestToEntity(SurveyReq request, Survey entity) {

        User creator = this.userRepository.findById(request.getCreador_id())
        .orElseThrow(() -> new IdNotFoundException("user"));

        List<Question> questions = new ArrayList<>(); 
        

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setCreationDate(request.getCreationDate());
        entity.setActive(request.getActive());
        entity.setCreator(creator);
        entity.setQuestion(questions);

        return entity;

    }

    private Survey find(Long id) {
        return this.surveyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("survey"));
    }

}
