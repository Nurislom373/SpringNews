package org.khasanof.swaggerui2.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/25/2023
 * <br/>
 * Time: 1:58 AM
 * <br/>
 * Package: org.khasanof.swaggerui2.domain
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String code;
}
