package org.khasanof.swaggerui2.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/22/2023
 * <br/>
 * Time: 11:20 AM
 * <br/>
 * Package: org.khasanof.swaggerui2.domain
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class Store {
    @Min(1)
    @NotNull
    private Long id;
    @NotBlank
    @Size(min = 3, max = 120)
    private String name;
    @NotBlank
    @Size(min = 9, max = 250)
    private String email;
    @NotNull
    @Min(1)
    private int employeeCount;
    private String desc;
}
