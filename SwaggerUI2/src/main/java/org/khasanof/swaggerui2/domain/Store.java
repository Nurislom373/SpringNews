package org.khasanof.swaggerui2.domain;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Store", description = "Store Entity")
public class Store {
    @Min(1)
    @NotNull
    @Parameter(description = "Store Identifier", required = true)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 120)
    @Parameter(description = "Store Name", required = true)
    private String name;
    @NotBlank
    @Size(min = 9, max = 250)
    @Parameter(description = "Store Email", required = true)
    private String email;
    @NotNull
    @Min(1)
    @Parameter(description = "Store EmployeeCount", required = true)
    private int employeeCount;
    @Parameter(description = "Store Description", required = false)
    private String desc;
}
