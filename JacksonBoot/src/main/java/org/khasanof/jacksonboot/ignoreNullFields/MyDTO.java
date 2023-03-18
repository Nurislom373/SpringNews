package org.khasanof.jacksonboot.ignoreNullFields;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/18/2023
 * <br/>
 * Time: 11:11 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.ignoreNullFields
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stringValue;

    private int intValue;
}
