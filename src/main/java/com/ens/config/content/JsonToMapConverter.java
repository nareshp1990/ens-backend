package com.ens.config.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter(autoApply = true)
@Slf4j
public class JsonToMapConverter implements AttributeConverter<String, Map<String, Object>> {

    @Override
    public Map<String, Object> convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return new HashMap<>();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(attribute, HashMap.class);
        } catch (IOException e) {
            log.error("Convert error while trying to convert string(JSON) to map data structure.");
        }
        return new HashMap<>();
    }

    @Override
    public String convertToEntityAttribute(Map<String, Object> dbData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dbData);
        } catch (JsonProcessingException e) {
            log.error("Could not convert map to json string.");
            return null;
        }
    }
}
