package org.khasanof.jacksonboot.objectMapper;

import lombok.*;

import java.io.Serializable;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/25/2023
 * <br/>
 * Time: 8:22 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.objectMapper
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {

    private String color;
    private String type;
    
}
