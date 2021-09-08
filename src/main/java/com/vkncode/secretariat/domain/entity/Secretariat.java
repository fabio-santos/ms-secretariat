package com.vkncode.secretariat.domain.entity;

import com.vkncode.secretariat.domain.dto.SecretariatDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Secretariat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String secretary;

    private boolean underInvestigation;

    private Destination destination;

    private int populationGrade;

    public Secretariat(SecretariatDTO dto) {
        this.id = dto.getId();
        this.secretary = dto.getSecretary();
        this.underInvestigation = dto.isUnderInvestigation();
        this.destination = dto.getDestination();
        this.populationGrade = dto.getPopulationGrade();
    }
}
