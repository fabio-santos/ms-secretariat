package com.vkncode.secretariat.domain.dto;

import com.vkncode.secretariat.domain.entity.Destination;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class SecretariatDTO {

    private Long id;

    @NotBlank
    private String secretary;

    private boolean underInvestigation;

    @NotNull
    private Destination destination;

    private int populationGrade;
}
