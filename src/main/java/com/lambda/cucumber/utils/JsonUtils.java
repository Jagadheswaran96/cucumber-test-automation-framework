package com.lambda.cucumber.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonUtils {

    public static String get(String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File("src/test/resources/testdata/loginData.json"))
                .get("validUser").get(key).asText();
    }
}