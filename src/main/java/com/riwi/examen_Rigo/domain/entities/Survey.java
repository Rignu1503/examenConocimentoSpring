package com.riwi.examen_Rigo.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "survey")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "creator_id",
        referencedColumnName = "id"
    )
    private User creator;

      @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "survey", fetch = FetchType.EAGER, orphanRemoval = false)
  private List<Question> question;
}
