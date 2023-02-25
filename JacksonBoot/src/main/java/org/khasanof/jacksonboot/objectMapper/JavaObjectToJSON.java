package org.khasanof.jacksonboot.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/25/2023
 * <br/>
 * Time: 8:27 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.objectMapper
 */
//@Component
@Slf4j
public class JavaObjectToJSON implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        readURLToObject();
        readJsonNode();
    }

    private void writeObjectToFile() throws IOException {
        Car car = new Car("yellow", "bmwTwo");
        objectMapper.writeValue(new File("JacksonBoot/src/main/resources/car.json"), car);
        log.info("Wrote Car with .json file");
    }

    private void readStringToObject() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car = objectMapper.readValue(json, Car.class);
        System.out.println("car = " + car);
    }

    private void readFileToObject() throws Exception {
        Car car = objectMapper.readValue(new File("JacksonBoot/src/main/resources/car.json"), Car.class);
        System.out.println("car = " + car);
    }

    private void readURLToObject() throws Exception {
        Car car = objectMapper.readValue(new URL("file:JacksonBoot/src/main/resources/car.json"), Car.class);
        System.out.println("car = " + car);
    }

    private void readJsonNode() throws Exception {
        JsonNode jsonNode = objectMapper.readTree(new File("JacksonBoot/src/main/resources/car.json"));
        String color = jsonNode.get("color").asText();
        System.out.println("color = " + color);
    }
}
