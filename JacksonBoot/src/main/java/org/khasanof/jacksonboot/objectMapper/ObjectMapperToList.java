package org.khasanof.jacksonboot.objectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/25/2023
 * <br/>
 * Time: 9:33 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.objectMapper
 */
//@Component
public class ObjectMapperToList implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        readCarList();
        readCarToMap();
    }

    private void readCarList() throws Exception {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<>(){});
        System.out.println("listCar = " + listCar);
    }

    private void readCarToMap() throws Exception {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>(){});
        System.out.println("map = " + map);
    }

    private void configurationObjectMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
    }
}
