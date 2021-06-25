package com.webbfontaine.githubanalysisfe.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonConverter {

    public static <T> T toObj(String is, Class<T> objClass) {
        T obj = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            obj = objectMapper.readValue(is, objClass);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Failed to convert json to object due to " + e.getMessage());
        }
        return obj;
    }

    public static <T> String toJson(T obj) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert from object to json due to " + e.getMessage());
        }
        return response;
    }
}
