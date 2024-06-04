package com.riwi.examen_Rigo.domain.entities;


import java.util.List;

import com.riwi.examen_Rigo.util.enums.QuestionsOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "question")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionsOption type;

    

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "question",
    fetch = FetchType.EAGER,
     cascade = CascadeType.ALL,
     orphanRemoval = false)
    private List<OptionQuestion> options;
    
    
    @ManyToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;
}
