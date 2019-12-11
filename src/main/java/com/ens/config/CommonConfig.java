package com.ens.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        // to enable pretty print
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        return objectMapper;
    }

}
