package org.khasanof.jacksonboot.customSDElizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.khasanof.jacksonboot.objectMapper.Car;

import java.io.IOException;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/25/2023
 * <br/>
 * Time: 9:44 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.customSDElizer
 */
public class CustomCarSerializer extends StdSerializer<Car> {

    public CustomCarSerializer() {
        this(null);
    }

    public CustomCarSerializer(Class<Car> t) {
        super(t);
    }

    @Override
    public void serialize(Car car, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("car_brand", car.getType());
        jsonGenerator.writeEndObject();
    }
}
