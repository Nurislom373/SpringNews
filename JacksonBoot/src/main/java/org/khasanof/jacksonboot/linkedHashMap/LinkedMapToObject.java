package org.khasanof.jacksonboot.linkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: Nurislom
 * <br/>
 * Date: 22.05.2023
 * <br/>
 * Time: 21:06
 * <br/>
 * Package: org.khasanof.jacksonboot.linkedHashMap
 */
public class LinkedMapToObject {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        LinkedHashMap<String, Map<String, String>> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("key", new HashMap<>(){{
            put("bfhdg1", "fdgfdgd");
            put("bfhdg2", "fdgfdgd");
            put("bfhdg3", "fdgfdgd");
            put("bfhdg4", "fdgfdgd");
            put("bfhdg5", "fdgfdgd");
            put("bfhdg6", "fdgfdgd");
        }});

        System.out.println("linkedHashMap = " + linkedHashMap);

    }

}
