package org.khasanof.ratelimitingwithspring.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/11/2023
 * <br/>
 * Time: 2:02 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.test
 */
@Getter
@Setter
public class AuthRequestDTO {
    private String username;
    private String password;
}
