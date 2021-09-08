package com.vkncode.secretariat.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UnderInvestigationDTO {

    @NotNull
    private boolean underInvestigation;
}
