package com.tryworkout.backend.domain.filestorage.service.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class EntityCreationConfig {
    @Bean
    public Map<Class<?>, EntityCreationStrategy<?>> creationStrategies(List<EntityCreationStrategy<?>> strategies) {
        return strategies.stream()
                .collect(Collectors.toMap(EntityCreationStrategy::getEntityType, Function.identity()));
    }

}
