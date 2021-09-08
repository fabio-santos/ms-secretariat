package com.vkncode.secretariat.service;

import com.vkncode.secretariat.domain.dto.SecretariatDTO;
import com.vkncode.secretariat.domain.entity.Destination;
import com.vkncode.secretariat.domain.services.SecretariatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SecretariatServiceTest {

    @Autowired
    private SecretariatService service;

    @Test
    public void defaultPath() {
        SecretariatDTO secretariatDTO = SecretariatDTO.builder()
                .secretary("fabio")
                .populationGrade(10)
                .underInvestigation(false)
                .destination(Destination.HEALTH)
                .build();

        service.save(secretariatDTO);
    }

    @Test
    public void duplicateTry() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SecretariatDTO secretariatDTO = SecretariatDTO.builder()
                    .secretary("john")
                    .populationGrade(8)
                    .underInvestigation(false)
                    .destination(Destination.INFRASTRUCTURE)
                    .build();

            service.save(secretariatDTO);

            secretariatDTO = SecretariatDTO.builder()
                    .secretary("mary")
                    .populationGrade(6)
                    .underInvestigation(false)
                    .destination(Destination.INFRASTRUCTURE)
                    .build();

            service.save(secretariatDTO);
        });
    }
}
