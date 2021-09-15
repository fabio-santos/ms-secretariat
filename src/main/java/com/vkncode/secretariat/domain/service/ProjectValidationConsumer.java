package com.vkncode.secretariat.domain.service;

import com.vkncode.secretariat.config.RabbitConfig;
import com.vkncode.secretariat.domain.dto.ProjectDTO;
import com.vkncode.secretariat.domain.entity.Secretariat;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProjectValidationConsumer {

    private RabbitTemplate rabbitTemplate;
    private SecretariatService service;
    private final String invalidSecretariat = "INVALID_SECRETARIAT";
    private final String secretariatUnderInvestigation = "SECRETARIAT_UNDER_INVESTIGATION";

    @RabbitListener(queues = RabbitConfig.SECRETARIAT_VALIDATION_QUEUE)
    private void validateProject(@Payload ProjectDTO project) {

        Optional<Secretariat> secretariat = service.findById(project.getSecretariat());

        if(secretariat.isEmpty()) {
            project.setState(invalidSecretariat);
            this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.ROUTING_KEY_ANY_TO_PROJECT, project);
            return;
        }

        if(secretariat.get().isUnderInvestigation()) {
            project.setState(secretariatUnderInvestigation);
            this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.ROUTING_KEY_ANY_TO_PROJECT, project);
            return;
        }

        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY_SECRETARIAT_TO_BUDGET, project);
    }
}
