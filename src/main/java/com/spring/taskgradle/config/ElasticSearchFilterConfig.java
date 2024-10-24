package com.spring.taskgradle.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ElasticSearchFilterConfig {

    @Value("${filter.active}")
    private boolean active;

    @Value("${filter.color}")
    private String color;

    @Value("${filter.available}")
    private boolean available;

   }
