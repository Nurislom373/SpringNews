package org.khasanof.swaggerui2.domain;

import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/22/2023
 * <br/>
 * Time: 3:43 PM
 * <br/>
 * Package: org.khasanof.swaggerui2.domain
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class Item {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
