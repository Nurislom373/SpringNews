package org.khasanof.websocket.ahc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.ahc
 * @since 9/2/2023 8:25 PM
 */
public class MessageConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> convertToMap(String message) {
        Assert.notNull(message, "message must not be null!");
        try {
            return objectMapper.readValue(replaceAllBackslashes(getMessageJson(message)), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T convertToType(String message, Class<T> clazz) {
        Assert.notNull(message, "message must not be null!");
        try {
            return objectMapper.readValue(replaceAllBackslashes(getMessageJson(message)), clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMessageJson(String message) {
        return message.substring(message.indexOf("{"), (message.lastIndexOf("}") + 1));
    }

    private String replaceAllBackslashes(String message) {
        return message.replaceAll("\\\\", "");
    }

}
