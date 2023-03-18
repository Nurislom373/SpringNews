package org.khasanof.jacksonboot.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/18/2023
 * <br/>
 * Time: 10:32 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.request
 */
public class RequestMain {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);

        Request request = new Request("BMW", new Date());
        String valueAsString = objectMapper.writeValueAsString(request);
        System.out.println("valueAsString = " + valueAsString);
    }

}
