package org.khasanof.jacksonboot.customSDElizer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.khasanof.jacksonboot.objectMapper.Car;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/25/2023
 * <br/>
 * Time: 9:47 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.customSDElizer
 */
@Component
public class CustomCarSerializerLiner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule("CustomCarSerializer", new Version(1, 0,0,
                null, null, null));

        simpleModule.addSerializer(Car.class, new CustomCarSerializer());
        objectMapper.registerModule(simpleModule);

        Car car = new Car("blue", "subaru");
        String value = objectMapper.writeValueAsString(car);
        System.out.println("value = " + value);
    }
}
