package org.khasanof.ratelimitingwithspring.core.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.khasanof.ratelimitingwithspring.core.AbstractConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 11:07 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.core.json
 */
@Component
public class JsonConfig implements AbstractConfig {

    @Value("${api.limit.path}")
    private String apiLimitPath;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ReadLimit> readLimitList() throws IOException {
        return objectMapper.readValue(getInputStream(), new TypeReference<>() {
        });
    }

    private InputStream getInputStream() {
        System.out.println("apiLimitPath = " + apiLimitPath);
        return getClass().getResourceAsStream(apiLimitPath);
    }

}
