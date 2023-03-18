package org.khasanof.jacksonboot.handlingCollections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khasanof.jacksonboot.objectMapper.Car;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/18/2023
 * <br/>
 * Time: 11:09 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.handlingCollections
 */
public class JsonCollectionMain {

    public static void main(String[] args) throws JsonProcessingException {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);
        System.out.println("cars = " + Arrays.toString(cars));

        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<>(){});
        System.out.println("listCar = " + listCar);
    }

}
