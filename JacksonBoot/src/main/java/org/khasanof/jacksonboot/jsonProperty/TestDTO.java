package org.khasanof.jacksonboot.jsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/27/2023
 * <br/>
 * Time: 5:01 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.jsonProperty
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
}
