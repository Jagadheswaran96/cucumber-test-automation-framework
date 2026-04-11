package com.lambda.cucumber.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonUtils {

    public static String get(String key) throws Exception {
    	
        ObjectMapper mapper = new ObjectMapper();

        String josnValue = mapper.readTree(new File("src/test/resources/testdata/loginData.json"))
                .get("validUser").get(key).asText();
        
        // Serialize Java object to JSON
        // String json = mapper.writeValueAsString(new File("src/test/resources/testdata/loginData.json"));
        // Deserialize JSON to Java object
        // mapper.readValue(json, Object.class);
        
        return josnValue;
    }
}