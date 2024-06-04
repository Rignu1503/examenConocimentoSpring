package com.riwi.examen_Rigo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.examen_Rigo.domain.entities.Survey;


public interface SurveyRepository extends JpaRepository<Survey, Long> {
    
}
